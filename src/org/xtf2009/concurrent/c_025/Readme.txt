总结：
1：对于map/set的选用：
非并发情况下：
HashMap/TreeMap/LinkedHashMap

较低并发下：
HashTable/Collections.synchronizedXXX()

高并发下：
ConcurrentHashMap/ConcurrentSkipListMap(如果需要排序)

2：对于List的选用
无并发：
ArrayList/LinkedList
并发：
Collections.synchronizedXXX()/Vector
高并发：
ConcurrentLinkedQueue（并发，加锁）
DelayQueue（执行定时任务）
BlockingQueue:（阻塞式）
    --LinkedBlockingQueue（无界队列）
    --ArrayBlockingQueue（有界队列）
    --TransferQueue(其中的transfer():生产者先检查有没有消费者在消费队列，如果有，则不再把对象放入队列，而是直接送给消费者；
                           找不到消费者时，线程会阻塞)
    --SynchronousQueue （是一种特殊的TransferQueue，容量为0，来的任何对象必须马上被消费掉，不然就会阻塞）
高读取，低写入：
CopyOnWriteList