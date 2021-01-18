package cn.ityihang.zblog.message.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: yihang
 * @Date: 2020/12/20 13:04
 * @Description:
 * @Version: 1.0
 */
public class JmsProduce {
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    public static final String QUQUE_NAME = "quque-01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        //创建会话
        // 两个参数：事务；签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建目的地
        Queue queue = session.createQueue(QUQUE_NAME);
        // 创建消息生产者
        MessageProducer messageProducer = session.createProducer(queue) ;
        for (int i = 1; i <= 6; i++) {
            // 创建消息
            TextMessage textMessage = session.createTextMessage("msg--------" + i);
            messageProducer.send(textMessage);
        }
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("消息发布到MQ完成");
    }
}
