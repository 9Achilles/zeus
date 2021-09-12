package zeus.entry.vo;

import lombok.Data;
import zeus.entry.base.ZeusBase;

import java.util.List;

@Data
public class ZeusTasksVO extends ZeusBase {

    private List<ZeusTaskVO> list;

}
