package Game;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound extends Thread {
    public boolean isStop = true;
    private URL url;
    private boolean loopNeed;

    //setter
    public void setStop(boolean isStoop) {
        this.isStop = isStoop;
    }

    //构造方法，含有两个参数，音频的路径和是否需要循环
    public Sound(URL url, boolean loopNeed) {
        this.url = url;
        this.loopNeed = loopNeed;
    }

    //重写run方法
    @Override
    public void run() {
        if (loopNeed) {
            //需要循环
            while(true){
                //设置音乐关停
                if(isStop){
                    //如果音乐关闭，则程序一直循环执行此语句
                    while(isStop){
                        try {
                            Thread.sleep(10);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
                playSound();
            }
        } else {
            //如果不需要循环，则直接调用playSound方法
            playSound();
        }
    }


    public void playSound() {
        //应用程序将音频字节写入源数据行，源数据行处理字节的缓冲并将其传送到混音器。
        //混音器可以将样本与其他来源的样本混合，然后将混音传送到目标，例如输出端口。
        try {
            //获取音频输入流
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            AudioFormat af = ais.getFormat();//获取AudioInputStream的格式
            SourceDataLine sdl = AudioSystem.getSourceDataLine(af);//获取可用于以对象指定的格式播放音频数据的源数据行
            sdl.open(af);//以指定格式打开
            sdl.start();//允许数据行执行i/o操作

            int nBytesRead;//创建读取音频字节的返回值
            byte[] Data = new byte[1024];//创建字节数组
            while ((nBytesRead = ais.read(Data, 0, Data.length)) != -1) {
                sdl.write(Data, 0, nBytesRead);//将数据写入混音器，开始播放
            }
            sdl.close();//关闭
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
