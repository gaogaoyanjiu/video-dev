package com.tdtk.utils;

import org.n3r.idworker.IdWorker;

public class IdWorkerUtils {


    /**
     * @Description: 获取全局唯一的Id
     */
    public static long getNextId(long id) throws Exception {
        IdWorker worker2 = new IdWorker(id);
        return worker2.nextId();
    }


    //测试IdWorker
    public static void main(String[] args) {
        IdWorker worker2 = new IdWorker(2);
        long upid = worker2.nextId();
        System.out.println(upid);

        long downid01 = worker2.nextId();
        System.out.println(downid01);

        long downid02 = worker2.nextId();
        System.out.println(downid02);

        long downid03 = worker2.nextId();
        System.out.println(downid03);
    }

}
