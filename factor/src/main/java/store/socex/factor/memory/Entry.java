package store.socex.factor.memory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Entry implements Serializable  {
    public static final short FLAG_PARENT = 1 << 1;
    public static final short FLAG_DIRECTORY = 1 << 2;
    public static final short FLAG_LINK = 1 << 3;
    public static final short FLAG_LAST_MODIFIED_TIME = 1 << 4;
    public static final short FLAG_CREATED_TIME = 1 << 5;
    public static final short FLAG_WRITE = 1 << 6;
    public static final short FLAG_EXECUTE = 1 << 7;
    public static final short FLAG_CONTENT_TYPE = 1 << 8;
    public static final short FLAG_DIGEST = 1 << 9;
    public static final int serialVersionUID = 0x00001010;
    public int id;
    public String name;
    public EntryType type;
    public Entry parent;
    public String contentType;
    public long lastModifiedTime = 0;
    public long createdTime = 0;
    public long size = 0;
    public boolean isLink;
    public boolean canWrite;
    public boolean canExecute;
    public byte[] digest;

    public void write(DataOutput output, FileSerialRegistry fileSerialRegistry) throws IOException {
        boolean hasParent = null != parent;
        boolean hasContentType = null != contentType;
        boolean hasDigest = null != digest;
        boolean isDirectory = EntryType.Directory == type;
        int options = (hasParent ? FLAG_PARENT : 0)
                | (hasContentType ? FLAG_CONTENT_TYPE : 0)
                | (hasDigest ? FLAG_DIGEST : 0)
                | (isLink ? FLAG_LINK : 0)
                | (createdTime > 0 ? FLAG_CREATED_TIME : 0)
                | (lastModifiedTime > 0 ? FLAG_LAST_MODIFIED_TIME : 0)
                | (canWrite ? FLAG_WRITE : 0)
                | (canExecute ? FLAG_EXECUTE : 0)
                | (isDirectory ? FLAG_DIRECTORY : 0);
        output.writeInt(serialVersionUID);
        output.writeShort(options);
        output.writeUTF(name);
        output.writeLong(size);
        if (EntryType.Directory == type) {
            if (!(id > 0)) {
                id = fileSerialRegistry.add(this);
            }
            output.writeInt(id);
        }
        if (hasContentType) {
            output.writeUTF(contentType);
        }
        if (hasParent) {
            output.writeInt(parent.id);
        }
        if (hasDigest) {
            for(var i = 0; i < digest.length; i++) {
                output.writeByte(digest[i]);
            }
        }
        if (lastModifiedTime > 0) {
            output.writeLong(lastModifiedTime);
        }
        if (createdTime > 0) {
            output.writeLong(createdTime);
        }
    }

    protected static boolean has(short options, short flag) {
        return 0 != (options & flag);
    }

    public void read(DataInput input, FileSerialRegistry fileSerialRegistry) throws IOException {
        int serialVersionUID = input.readInt();
        if (serialVersionUID != Entry.serialVersionUID) {
            throw new IOException("Invalid serial version ID");
        }
        short options = input.readShort();
        name = input.readUTF();
        size = input.readLong();
        if (has(options, FLAG_DIRECTORY)) {
            id = input.readInt();
        }
        if (has(options, FLAG_CONTENT_TYPE)) {
            contentType = input.readUTF();
        }
        if (has(options, FLAG_PARENT)) {
            parent = fileSerialRegistry.get(input.readInt());
        }
        if (has(options, FLAG_DIGEST)) {
            digest = new byte[16];
            input.readFully(digest);
        }
        if (has(options, FLAG_LAST_MODIFIED_TIME)) {
            lastModifiedTime = input.readLong();
        }
        if (has(options, FLAG_CREATED_TIME)) {
            createdTime = input.readLong();
        }
    }

    public Entry(File file, File parent) {
        Path path = Paths.get(file.getName());
        name = path.getFileName().toString();
    }
}
