# 1 线程启动
* extends Thread
* implements Runnable
* implements Callable
* implements ThreadFactory
* new ThreadPoolExecutor

# 2 线程状态
```
NEW -> RUNNABLE -> TERMINATED
NEW -> RUNNABLE -> WAITING -> RUNNABLE -> TERMINATED
NEW -> RUNNABLE -> TIMED_WAITING -> RUNNABLE -> TERMINATED
```
# 3 线程互斥
* synchronized
* ReetrantLock、ReetrantReadWriteLock

# 4 线程同步
* CountDownLatch
* CyclicBarrier
* Semaphore
* Phaser

# 5 容器
* CopyOnWriteArrayList、CopyOnWriteArraySet
* ConcurrentHashMap
* ArrayBlockingQueue
* LinkedBlockingQueue
* PriortyBlockingQueue