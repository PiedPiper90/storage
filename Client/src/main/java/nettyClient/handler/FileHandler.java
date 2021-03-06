package nettyClient.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import nettyClient.util.Response;

import java.io.File;
import java.io.RandomAccessFile;

public class FileHandler extends SimpleChannelInboundHandler<Response> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Response msg) throws Exception {
        if (msg.getCommand().equals("/fne")) {
            System.out.println("File not exist");
        } else if (msg.getCommand().equals("/getlist")) {
            System.out.println(msg.getFilename());
        } else if (msg.getCommand().equals("/download")) {
            try (RandomAccessFile accessFile = new RandomAccessFile("D:\\client\\" + msg.getFilename(), "rw")) {
                accessFile.seek(msg.getPosition());
                accessFile.write(msg.getFile());
            }
        }else if(msg.getCommand().equals("/dc")){
            System.out.println("Download complete");
        }else if(msg.getCommand().equals("/ff")){
            File file= new File("D:\\client\\" + msg.getFilename());
            file.delete();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
