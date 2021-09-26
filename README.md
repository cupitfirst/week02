# week02

## 作业说明：
* 4.（必做）根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub。  
  - 串行GC：堆内存越大，GC次数越少，GC pause 时长增加。
  - 并行GC：堆内存越大，GC次数越少，GC pause 时长增加，比串行 GC pause 时长短。
  - CMSGC：堆内存越大，GC次数越少，GC pause 时长增加，与并行GC pause 时长接近。
  - G1GC：堆内存越大，GC次数越少，GC pause 时长增长慢，GC 效率非常高，4G内存情况下，GC 时长 10ms 以内。
  - Gateway sb压测，内存从 512M 增长到 2G，在同等环境下，使用不同 GC 策略，对压测影响不大，RPS 在200范围内波动。

* 6.（必做）写一段代码，使用 HttpClient 或 OkHttp 访问  http://localhost:8801 ，代码提交到 GitHub  
  - HttpClient 实现见 com.bh1ofp.week02.HttpClientDemo  
  - OKHttp 实现见 com.bh1ofp.week02.OkHttpdemo
  - 使用的是 SocketDemo03 实现，访问  http://localhost:8804 服务
