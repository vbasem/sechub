// SPDX-License-Identifier: MIT
package com.daimler.sechub.sharedkernel.usecases.job;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.daimler.sechub.sharedkernel.Step;
import com.daimler.sechub.sharedkernel.usecases.UseCaseDefinition;
import com.daimler.sechub.sharedkernel.usecases.UseCaseGroup;
import com.daimler.sechub.sharedkernel.usecases.UseCaseIdentifier;
/* @formatter:off */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@UseCaseDefinition(
		id=UseCaseIdentifier.UC_ADMIN_LISTS_ALL_RUNNING_JOBS,
		group=UseCaseGroup.JOB_ADMINISTRATION,
		title="Admin lists all running jobs",
		description="job/admin_lists_all_running_jobs.adoc")
public @interface UseCaseAdministratorListsAllRunningJobs {

	Step value();
}
/* @formatter:on */
