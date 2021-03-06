// SPDX-License-Identifier: MIT
package com.daimler.sechub.domain.notification.superadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.daimler.sechub.domain.notification.NotificationConfiguration;
import com.daimler.sechub.domain.notification.email.EmailService;
import com.daimler.sechub.domain.notification.email.MailMessageFactory;
import com.daimler.sechub.sharedkernel.Step;
import com.daimler.sechub.sharedkernel.usecases.admin.schedule.UseCaseAdministratorEnablesSchedulerJobProcessing;

@Service
public class InformAdminsThatSchedulerJobProcessingHasBeenEnabledService {

	@Autowired
	private MailMessageFactory factory;

	@Autowired
	private NotificationConfiguration notificationConfiguration;

	@Autowired
	private EmailService emailService;

	@UseCaseAdministratorEnablesSchedulerJobProcessing(@Step(number = 4, next = {
			Step.NO_NEXT_STEP }, name = "Inform sechub admins that scheduler job processing has been enabled"))
	public void notify(String baseUrl) {

		SimpleMailMessage message = factory.createMessage("Scheduler job processing enabled");

		message.setTo(notificationConfiguration.getEmailAdministrators());
		message.setText(createEmailContent(baseUrl));

		emailService.send(message);

	}

	private String createEmailContent(String baseUrl) {
		StringBuilder emailContent = new StringBuilder();
		emailContent.append("Scheduler job processing has been enabled at sechub for environment (base url):").append(baseUrl).append("\n");

		String text = emailContent.toString();
		return text;
	}

}
