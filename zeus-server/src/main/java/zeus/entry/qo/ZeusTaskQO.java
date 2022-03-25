//package zeus.entry.qo;
//
//import lombok.Data;
//import zeus.constant.ZeusZKDeptConstant;
//import zeus.entry.base.ZeusBase;
//
//import java.util.Map;
//
///**
// * 任务
// * task_id
// * task_name
// * dept
// * application_name
// * corn
// * policy (这一版没有)
// * retry
// * task_url
// * task_method
// * task_head
// * task_body
// * open true false
// * description;
// */
//
//@Data
//public class ZeusTaskQO extends ZeusBase {
//
//    private Long taskId;
//
//    private String taskName;
//
//    private String dept = ZeusZKDeptConstant.ZEUS_PUBLIC_DEPT;
//
//    private String name;
//
//    private String corn;
//
//    private Integer policy;
//
//    private Integer retry;
//
//    private String url;
//
//    private String method;
//
//    private Map<String,String> head;
//
//    private Map<String,String> body;
//
//    private String description;
//
//    private Boolean open;
//
//}
