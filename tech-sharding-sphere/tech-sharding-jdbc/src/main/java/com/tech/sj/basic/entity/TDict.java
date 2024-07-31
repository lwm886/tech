package com.tech.sj.basic.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lw
 * @since 2024-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TDict implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long dictId;

    private String ustatus;

    private String uvalue;


}
