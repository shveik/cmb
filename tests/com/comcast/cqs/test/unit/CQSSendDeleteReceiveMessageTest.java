/**
 * Copyright 2012 Comcast Corporation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.comcast.cqs.test.unit;

import static org.junit.Assert.*;

import org.junit.Test;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.comcast.cmb.test.tools.CMBAWSBaseTest;

public class CQSSendDeleteReceiveMessageTest extends CMBAWSBaseTest {


    @Test
    public void testSendDeleteReceiveMessageServlet() {
    	
    	String queueUrl = getQueueUrl(1, USR.USER1);
    	//String message = "hello world!!!";
    	
    	String message = "2\n"
+"4|arn:cmb:cqs:ccp:381515276957:a|arn:cmb:cns:ccp:381515276957:test:7334d7cc-ea66-4260-8b66-f28a18978e11|true\n"
+"0|http://localhost:6059/Endpoint/recv/a|arn:cmb:cns:ccp:381515276957:test:bc4d8987-12fa-45e4-9569-595fd35cff02|true\n"
+"1\n"
+"\n"
+"*\n"
+"arn:cmb:cns:ccp:381515276957:test\n"
+"1381614162483\n"
+"381515276957\n"
+"20db7499-8f6e-4116-8f77-91a69582c40b\n"
+"Notification\n"
+"{ \n"
+"  default: <enter your message here>,\n"
+"  email: <enter your message here>,\n"
+"  cqs: <enter your message here>,\n"
+"  http: <enter your message here>,\n"
+"  https: <enter your message here> \n"
+"}";
    	
    	cqs1.sendMessage(new SendMessageRequest(queueUrl, message));
    	
    	ReceiveMessageResult result = cqs1.receiveMessage(new ReceiveMessageRequest(queueUrl));
    	
    	if (result != null && result.getMessages().size() == 1) {
    		assertTrue("wrong message content: " + result.getMessages().get(0).getBody(), message.equals(result.getMessages().get(0).getBody()));
    		logger.info("event=message_found queue=" + queueUrl + " message=" + message);
    	} else {
    		fail("no message found in " + queueUrl);
    	}
    }
}
