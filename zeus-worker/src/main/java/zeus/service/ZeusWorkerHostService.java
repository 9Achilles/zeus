package zeus.service;


import zeus.exception.ZeusWorkerException;


public interface ZeusWorkerHostService {

    String getHost(String dept,String name) throws ZeusWorkerException;

}
