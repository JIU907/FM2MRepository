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

        /**
         * G1:
         *  > 将内存划分为一个个region
         *  > 初始标记 - 并发标记 - 最终标记 -筛选回收(根据每个region的收益与设置的停顿时间进行回收个别region)
         *  > 优点: 根据每个region的收益进行差别回收,对程序响应时间上有较高的提升
         *  > 缺点: G1的卡表更加复杂,每个region都持有一份卡表,占用了大量的内存
         *
         * Shenandoah:
         *  > 初始标记 - 并发标记 - 最终标记
         *      - 并发清理(清理那些没有存活对象的region)
         *      - 并发回收(将回收集内的存活对象复制到一份未被使用的region中,使用到了"转发指针"来保证用户线程与GC线程并发读写的问题)
         *      - 引用更新(就是将旧地址对象指针更新为新复制对象的指针)
         * ZGC:
         *  > 对比前面的几款GC(将引用链信息存储在对象中)ZGC采取将引用链存储在指针上(染色指针)
         *  > TODO
         */
    }
}
