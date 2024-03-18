package Game;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class BGM extends Thread {
    //构造方法
    public BGM() {
    }

    //重写run方法，循环调用playMusic
    @Override
    public void run() {
        while (true) {
            playMusic();
        }
    }

    public void playMusic() {
        //应用程序将音频字节写入源数据行，源数据行处理字节的缓冲并将其传送到混音器。
        //混音器可以将样本与其他来源的样本混合，然后将混音传送到目标，例如输出端口。
        try {
            //获取音频输入流
            AudioInputStream ais = AudioSystem.getAudioInputStream(this.getClass().getResource("/Music/bgm.wav"));
            AudioFormat af = ais.getFormat();//获取AudioInputStream的格式
            SourceDataLine sdl = AudioSystem.getSourceDataLine(af);//获取可用于以对象指定的格式播放音频数据的源数据行
            sdl.open(af);//以制定格式打开
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
