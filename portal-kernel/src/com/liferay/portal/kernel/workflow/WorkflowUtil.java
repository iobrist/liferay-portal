/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.kernel.workflow;

/**
 * <a href="WorkflowUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * This utility class defines the main entry point to the workflow system in
 * Liferay as it provides static access to different manager of the workflow
 * system. A more detailed description can be found on the manager interfaces
 * being returned by the static accessory methods in this utility class.
 * </p>
 *
 * @author Micha Kiener
 */
public class WorkflowUtil {

	/**
	 * <p>
	 * Creates a user credential representing the user with the specified id,
	 * its set of assigned roles and additional information requested through
	 * the {@link UserCredentialFactory#createCredential(long)}.
	 * </p>
	 *
	 * <p>
	 * The user credential is most likely be used to represent the
	 * <code>callingUserId</code> on a workflow API invocation to be passed on
	 * to the underlying workflow engine. Some engines want to track users
	 * invoking methods and thus executing activities, creating history entries
	 * or starting new workflows. Thats why most methods of the workflow API are
	 * taking the caller's user id to pass it on to the engine through a {@link
	 * UserCredential}.
	 * </p>
	 *
	 * @return the user credential object acting as the container representing
	 *		   the set of roles and additional information for the given user
	 */
	public static UserCredential createUserCredential(long userId)
		throws WorkflowException {
		return _userCredentialFactory.createCredential(userId);
	}

	/**
	 * The task instance manager is the entry point to deal with tasks created
	 * out of a workflow definition, always attached to a workflow instance. The
	 * manager supports method to retrieve, assign and approve tasks.
	 *
	 * @return the task instance manager
	 */
	public static TaskInstanceManager getTaskInstanceManager() {
		return _taskInstanceManager;
	}

	/**
	 * The workflow definition manager is the entry point to deploy workflow
	 * definitions. A workflow definition is the model used to describe the
	 * workflow.
	 *
	 * @return the workflow definition manager
	 */
	public static WorkflowDefinitionManager getWorkflowDefinitionManager() {
		return _workflowDefinitionManager;
	}

	/**
	 * The workflow definition mapper can be used for mapping a domain class to
	 * a certain workflow definition name.
	 *
	 * @return the workflow mapper
	 */
	public static WorkflowDefinitionMapper getWorkflowDefinitionMapper() {
		return _workflowDefinitionMapper;
	}

	/**
	 * The workflow instance manager is the entry point to deal with existing
	 * workflow instances and how to retrieve them. More details about a
	 * workflow instance and how to start them and retrieve them can be read
	 * within the javadoc of {@link WorkflowInstanceManager}.
	 *
	 * @return the workflow instance manager
	 */
	public static WorkflowInstanceManager getWorkflowInstanceManager() {
		return _workflowInstanceManager;
	}

	public WorkflowUtil(
		WorkflowDefinitionManager workflowDefinitionManager,
		WorkflowInstanceManager workflowInstanceManager,
		TaskInstanceManager taskInstanceManager,
		WorkflowDefinitionMapper workflowDefinitionMapper,
		UserCredentialFactory userCredentialFactory){

		_workflowDefinitionManager=workflowDefinitionManager;
		_workflowInstanceManager=workflowInstanceManager;
		_taskInstanceManager=taskInstanceManager;
		_workflowDefinitionMapper=workflowDefinitionMapper;
		_userCredentialFactory=userCredentialFactory;
	}
	public static void setTaskInstanceManager(
		TaskInstanceManager taskInstanceManager) {
		_taskInstanceManager = taskInstanceManager;
	}

	public static void setUserCredentialFactory(UserCredentialFactory factory) {
		_userCredentialFactory = factory;
	}

	public static void setWorkflowDefinitionManager(
		WorkflowDefinitionManager workflowDefinitionManager) {
		_workflowDefinitionManager = workflowDefinitionManager;
	}

	public static void setWorkflowDefinitionMapper(
			WorkflowDefinitionMapper workflowDefinitionMapper) {
		_workflowDefinitionMapper = workflowDefinitionMapper;
	}

	public static void setWorkflowInstanceManager(
		WorkflowInstanceManager workflowInstanceManager) {
		_workflowInstanceManager = workflowInstanceManager;
	}

	private static TaskInstanceManager _taskInstanceManager;
	private static UserCredentialFactory _userCredentialFactory;
	private static WorkflowDefinitionManager _workflowDefinitionManager;
	private static WorkflowDefinitionMapper _workflowDefinitionMapper;
	private static WorkflowInstanceManager _workflowInstanceManager;

}