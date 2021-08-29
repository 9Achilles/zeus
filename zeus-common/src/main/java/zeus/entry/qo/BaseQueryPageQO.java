package zeus.entry.qo;

import lombok.Data;

@Data
public class BaseQueryPageQO {

    private Integer pageSize = 10;

    private Integer pageIndex = 1;

}
