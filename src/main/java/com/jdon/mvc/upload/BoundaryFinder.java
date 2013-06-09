package com.jdon.mvc.upload;

/**
 * 域定位
 * User: Asion
 * Date: 13-6-9
 * Time: 下午3:55
 */
public class BoundaryFinder {

    private byte[] boundary;

    private int[] charTable;

    private int[] offsetTable;

    public BoundaryFinder(byte[] boundary) {
        super();
        this.boundary = boundary;
        charTable = makeCharTable(boundary);
        offsetTable = makeOffsetTable(boundary);
    }

    public int getBoundaryLength() {
        return boundary.length;
    }

    /**
     * Returns the index within this string of the first occurrence of the
     * specified substring. If it is not a substring, return -1.
     *
     * @param buffer The byte buffer to be scanned
     * @return The start index of the boundary
     */
    public int indexOf(byte[] buffer) {
        return indexOf(buffer, 0);
    }

    /**
     * Returns the index within this string of the first occurrence of the
     * specified substring. If it is not a substring, return -1.
     *
     * @param buffer The string to be scanned
     * @return The start index of the boundary
     */
    public int indexOf(byte[] buffer, int start) {
        return indexOf(buffer, start, buffer.length);
    }

    /**
     * Returns the index within this string of the first occurrence of the
     * specified substring. If it is not a substring, return -1.
     *
     * @param buffer The byte buffer to be scanned
     * @return The start index of the boundary
     */
    public int indexOf(byte[] buffer, int start, int end) {
        if (boundary.length == 0) {
            return 0;
        }
        for (int i = boundary.length - 1 + start, j; i < end; ) {
            for (j = boundary.length - 1; boundary[j] == buffer[i]; --i, --j) {
                if (j == 0) {
                    return i;
                }
            }
            // i += needle.length - j; // For naive method
            int offset = Math.max(offsetTable[boundary.length - 1 - j], charTable[buffer[i] < 0 ? buffer[i] + 256 : buffer[i]]);
            i += offset;
        }
        return -1;
    }

    /**
     * Makes the jump table based on the mismatched character information.
     */
    private int[] makeCharTable(byte[] needle) {
        final int ALPHABET_SIZE = 256;
        int[] table = new int[ALPHABET_SIZE];
        for (int i = 0; i < table.length; ++i) {
            table[i] = needle.length;
        }
        for (int i = 0; i < needle.length - 1; ++i) {
            table[needle[i]] = needle.length - 1 - i;
        }
        return table;
    }

    /**
     * Makes the jump table based on the scan offset which mismatch occurs.
     */
    private int[] makeOffsetTable(byte[] needle) {
        int[] table = new int[needle.length];
        int lastPrefixPosition = needle.length;
        for (int i = needle.length - 1; i >= 0; --i) {
            if (isPrefix(needle, i + 1)) {
                lastPrefixPosition = i + 1;
            }
            table[needle.length - 1 - i] = lastPrefixPosition - i + needle.length - 1;
        }
        for (int i = 0; i < needle.length - 1; ++i) {
            int slen = suffixLength(needle, i);
            table[slen] = needle.length - 1 - i + slen;
        }
        return table;
    }

    /**
     * Is needle[p:end] a prefix of needle?
     */
    private boolean isPrefix(byte[] needle, int p) {
        for (int i = p, j = 0; i < needle.length; ++i, ++j) {
            if (needle[i] != needle[j]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the maximum length of the substring ends at p and is a suffix.
     */
    private int suffixLength(byte[] needle, int p) {
        int len = 0;
        for (int i = p, j = needle.length - 1; i >= 0 && needle[i] == needle[j]; --i, --j) {
            len += 1;
        }
        return len;
    }

}
