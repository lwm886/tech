package com.tech.cpu;

import lombok.Data;

/**
 * @author lw
 * @since 2023-01-05
 */
@Data
public class UserVo {
    public byte[] bytes;
    private byte[] data=new byte[1000];
}
