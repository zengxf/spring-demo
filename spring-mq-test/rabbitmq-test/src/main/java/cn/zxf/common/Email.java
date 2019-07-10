package cn.zxf.common;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            to;
    private String            body;

}
