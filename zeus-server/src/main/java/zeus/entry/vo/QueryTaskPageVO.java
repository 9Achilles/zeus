package zeus.entry.vo;

import lombok.Data;

import java.util.List;

@Data
public class QueryTaskPageVO {

    private List<QueryTaskListPageVO> list;

    private Long counts;
}
