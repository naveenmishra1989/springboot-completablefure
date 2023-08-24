package com.example.naveen.service;

import com.example.naveen.model.EmpName;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class EmpNameService {

    private List<EmpName> empNameList;

    private static void accept(EmpName data) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("EmpNameService:" + data + " " + Thread.currentThread().getName());
    }

    @PostConstruct
    void init() {
        empNameList = List.of(
                new EmpName("naveen", "Kumar"),
                new EmpName("naveen", "mishra"),
                new EmpName("naveen", "shaw"));
    }

    @Async
    public CompletableFuture<List<EmpName>> getEmpNameList() {
        empNameList.forEach(EmpNameService::accept);
        return CompletableFuture.completedFuture(empNameList);
    }
}
