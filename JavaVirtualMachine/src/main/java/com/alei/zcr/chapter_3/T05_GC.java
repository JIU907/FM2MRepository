package com.alei.zcr.chapter_3;

/**
 * @author LeiLiMin
 * @date: 2022/8/17
 */
public class T05_GC {
    public static void main(String[] args) {
        /**
         * 经典垃圾收集器
         * 1.serial: 新生代-单线程收集
         *          > STW - 标记
         *          > STW - 清除
         * 2.ParNew: 新生代-多线程并行收集
         *          > STW - 多线程标记
         *          > STW -多线程清除
         * 3.Parallel Scavenge: 新生代-多线程并行收集
         *          > STW - 多线程标记
         *          > STW -多线程清除
         *          > 主要关注吞吐量
         *
         * 4.serial-Old:  老年代-单线程收集
         *          > STW - 标记
         *          > STW - 整理
         * 5.Parallel-Old: 老年代-多线程收集
         * 6.CMS: 老年代-多线程收集
         *          > STW - 初始标记(只标记与GCRoots直接关联的对象)
         *          > 并发 - 标记
         *          > STW - 重新标记,参考三色标记中的"增量更新"和"原始快照"
         *          > 并发 - 清除
         *       > 优点: 最为耗时的标记阶段与清除阶段都是与用户线程并发进行的
         *              所以挺短时间上的尖峰表小了
         *       > 缺点1: 由于默认配置(处理器核心+3)/4的处理器
         *               导致核心数少时与用户程序抢占CPU资源导致吞吐量降低
         *               核心数多时,又会增加线程上下文切换的开销
         *               导致用户程序执行的速度变慢
         *       > 缺点2: CMS在并发标记阶段无法解决"浮动垃圾",导致垃圾对象未能即时回收
         *       > 缺点3: CMS采取"标记-清除算法",就会导致大量不规整的内存区域
         *               在CMS-GC之后无法为对象在老年代分配空间时,就会被动的使用serial-Old来进行垃圾回收,此时STW的成本也较高
         *
         */
    }
}
