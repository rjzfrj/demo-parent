package com.jason.distributedlock;

import java.io.IOException;

import com.jason.pandaLock.core.exception.PandaLockException;
import com.jason.pandaLock.core.serverImpl.ZkPandaLock;

/**
 * 多线程并发执行Task
 */
public class LockTest 
{
	public static void main(String[] args) throws IOException, InterruptedException, PandaLockException {
		final int j=0;
		for (  int i = 0; i < 10; i++) {
			new Thread(new Task(j)).start();
        }
	}
}
