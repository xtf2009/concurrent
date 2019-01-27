##理解线程池需要的基础概念
#### Executor
是一个interface，只有一个execute方法，用来运行Runnable  

####ExecutorService
也是一个interface，继承于Executor。  
不同点主要在于ExecutorService多了submit方法，既可以运行Runnable，也可以运行Callable  

####Callable
和Runnable类似，都是设计来给线程调用的。  

不同点：  
1. Runnable的run方法没有返回值，而Callable的call方法可以有返回值；
2. Runnable的run方法没法抛出异常，而Callable的call方法可以。

####Executors
这是一个多线程常用的工具类。

####ThreadPool线程池
线程池指的是用来容纳和运行线程的容器。  
线程池的意义在于对线程进行重用，避免反复创建和销毁线程，配置合理的线程池可以大幅提升并发性能。  
一个线程池维护着两个队列，一个是待执行任务队列，一个是已结束任务队列。

####Future
1. FutureTask   
用来把Callable包装成一个任务；  
表示未来的任务（因为需要等执行完获取返回值），里面可以装一个Callable类型，提供返回值；  

2. Future  
 是对Callable执行结果的封装

##常见线程池

####FixedThreadPool
* 创建重用固定数量线程的线程池，不能随时新建线程
* 当所有线程都处于活动状态时，如果提交了其他任务，他们将在队列中等待一个线程可用
* 线程会一直存在，直到调用shutdown

####CachedThreadPool
不限个数的线程池，有需求就会创建新的线程，直到系统硬件承受能力的极限（或者Int的最大范围）。  

* 重用之前的线程
* 适合执行许多短期异步任务的程序。
* 调用 execute() 将重用以前构造的线程
* 如果没有可用的线程，则创建一个新线程并添加到池中
* 默认为60s未使用就被终止和移除
* 长期闲置的池将会不消耗任何资源

####SingleThreadExecutor
* 在任何情况下都不会有超过一个任务处于活动状态
* 与newFixedThreadPool(1)不同是不能重新配置加入线程，使用FinalizableDelegatedExecutorService进行包装
* 能保证执行顺序，先提交的先执行。
* 当线程执行中出现异常，去创建一个新的线程替换之

####ScheduledThreadPool
* 设定延迟时间，定期执行
* 空闲线程会进行保留

####WorkStealingPool
* 获取当前可用的线程数量进行创建作为并行级别
* 使用ForkJoinPool

####ForkJoinPool
* 将任务切割成子任务并行运行
* 适用于大规模的数据计算

##线程池的实现原理
FixedThreadPool/CachedThreadPool/SingleThreadExecutor/ScheduledThreadPool线程池都是基于ThreadPoolExecutor实现的。  
任务队列都是BlockingQueue（阻塞式队列）

##五种线程池的适应场景
1. newCachedThreadPool：  
用来创建一个可以无限扩大的线程池，适用于服务器负载较轻，执行很多短期异步任务。
2. newFixedThreadPool：  
创建一个固定大小的线程池，因为采用无界的阻塞队列，所以实际线程数量永远不会变化，适用于可以预测线程数量的业务中，或者服务器负载较重，对当前线程数量进行限制。
3. newSingleThreadExecutor：  
创建一个单线程的线程池，适用于需要保证顺序执行各个任务，并且在任意时间点，不会有多个线程是活动的场景。
4. newScheduledThreadPool：  
可以延时启动，定时启动的线程池，适用于需要多个后台线程执行周期任务的场景。
5. newWorkStealingPool：  
创建一个拥有多个任务队列的线程池，可以减少连接数，创建当前可用cpu数量的线程来并行执行，适用于大耗时的操作，可以并行来执行
