package fr.treeptik.centreformation.jms;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.treeptik.centreformation.exception.ServiceException;
import fr.treeptik.centreformation.model.Session;
import fr.treeptik.centreformation.service.SessionService;

@MessageDriven(name = "sessionListenerQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/MyQueueCf"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class SessionServiceListener implements MessageListener {
	@Inject
	private SessionService sessionService;

	@Override
	public void onMessage(Message message) {

		TextMessage textMessage = (TextMessage) message;
		ObjectMapper mapper = new ObjectMapper();

		try {
			Session session = mapper.readValue(textMessage.getText(),
					Session.class);
			sessionService.save(session);

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
