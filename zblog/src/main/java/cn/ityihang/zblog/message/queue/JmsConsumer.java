package cn.ityihang.zblog.message.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @Author: yihang
 * @Date: 2020/12/20 13:05
 * @Description:
 * @Version: 1.0
 */
public class JmsConsumer {
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    public static final String QUQUE_NAME = "quque-01";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //创建会话
        // 两个参数：事务；签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建目的地
        Queue queue = session.createQueue(QUQUE_NAME);
//        创建消息消费者
        MessageConsumer consumer = session.createConsumer(queue);
//        while (true) {
//            TextMessage testMessage = (TextMessage) consumer.receive(6000L);
//            if (null != testMessage) {
//                System.out.println("*********消息消费者收到消息： " + testMessage.getText());
//            } else {
//                break;
//            }
//        }
//        consumer.close();
//        session.close();
//        connection.close();

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (null!=message && message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("*********消息消费者收到消息：MessageListener " + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}
