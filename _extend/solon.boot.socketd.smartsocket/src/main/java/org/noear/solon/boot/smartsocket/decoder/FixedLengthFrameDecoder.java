package org.noear.solon.boot.smartsocket.decoder;

import java.nio.ByteBuffer;

public class FixedLengthFrameDecoder {
    private final int length;
    private final ByteBuffer buffer;

    public FixedLengthFrameDecoder(int frameLength) {
        length = frameLength;
        buffer = ByteBuffer.allocate(frameLength);
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public int getLength() {
        return length;
    }

    public boolean read(ByteBuffer byteBuffer) {
        int len0 = length - buffer.position();
        if (len0 > byteBuffer.remaining()) {
            buffer.put(byteBuffer.array());
            return false;
        } else {
            byte[] bytes = new byte[len0];
            byteBuffer.get(bytes, 0, len0);
            buffer.put(bytes);
            return true;
        }
    }
}
