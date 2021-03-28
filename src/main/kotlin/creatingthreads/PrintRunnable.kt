package creatingthreads

class PrintRunnable : Runnable {
    override fun run() {
        println("Print from thread with runnable: ${Thread.currentThread().name}")
    }
}