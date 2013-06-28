/** -----------------------------------------------------------------------
  *
  *   com.logicacmg.koa.scheduler.job.RemoveKiesLijstJob.java
  *
  * -----------------------------------------------------------------------
  * 
  *  (c) 2003  Ministerie van Binnenlandse Zaken en Koninkrijkrelaties
  *
  *  Project		: Kiezen Op Afstand (KOA)
  *  Project Number	: ECF-2651
  *  
  *  History:
  *  Version	Date		Name		Reason
  * ---------------------------------------------------------
  *  0.1		07-05-2003	MKu			First implementation
  * -----------------------------------------------------------------------
  */
package com.logicacmg.koa.scheduler.jobs;
import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.logica.eplatform.error.EPlatformException;
import com.logica.eplatform.util.LogHelper;
import com.logicacmg.koa.constants.ErrorConstants;
import com.logicacmg.koa.constants.JNDIProperties;
import com.logicacmg.koa.databeheer.ejb.session.KiesRegisterAdmin;
import com.logicacmg.koa.databeheer.ejb.session.KiesRegisterAdminHome;
import com.logicacmg.koa.exception.KOADataBeheerException;
import com.logicacmg.koa.scheduler.AbstractJob;
import com.logicacmg.koa.scheduler.SchedulerConstants;
import com.logicacmg.koa.utils.KOALogHelper;
/**
 * Job used by the scheduler to schedule the remove
 * the kies register
 * 
 * @author KuijerM
 */
public class RemoveKiesRegisterJob extends AbstractJob
{
	/**
	 * Constructor for the RemoveKiesRegisterJob
	 */
	public RemoveKiesRegisterJob()
	{
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[RemoveKiesRegisterJob] Constructor...");
	}
	/**
	 * Executes the job
	 * 
	 * @return boolean indicating the success of the execution
	 * 
	 * @throws EPlatformException If something goes wrong
	 */
	public boolean execute() throws EPlatformException
	{
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[RemoveKiesRegisterJob.execute] Start executing the importing...");
		boolean bResult = true;
		Hashtable htProps = new Hashtable();
		htProps.put(
			Context.INITIAL_CONTEXT_FACTORY,
			JNDIProperties.getProperty(
				JNDIProperties.DATABEHEER_CONTEXT_FACTORY));
		htProps.put(
			Context.PROVIDER_URL,
			JNDIProperties.getProperty(JNDIProperties.DATABEHEER_PROVIDER));
		//We can get parameters from context
		int iTempDataID = 0;
		try
		{
			iTempDataID =
				Integer.parseInt(
					context.getProperty(SchedulerConstants.TEMP_DATA_ID_KEY));
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"[RemoveKiesRegisterJob.execute] got temp data id "
					+ context.getProperty(SchedulerConstants.TEMP_DATA_ID_KEY));
		}
		catch (NumberFormatException nfe)
		{
			String[] params =
				{ context.getProperty(SchedulerConstants.TEMP_DATA_ID_KEY)};
			KOALogHelper.logErrorCode(
				"RemoveKiesRegisterJob,execute",
				ErrorConstants.ERR_NUMBER_FORMAT,
				params,
				nfe);
			throw new KOADataBeheerException(
				KOADataBeheerException.EJBEXCEPTION,
				nfe);
		}
		//implement the execution here...
		try
		{
			InitialContext ic = new InitialContext(htProps);
			Object obj =
				ic.lookup(
					JNDIProperties.getProperty(
						JNDIProperties.DATABEHEER_KIESREGISTER_ADMIN));
			KiesRegisterAdminHome home =
				(KiesRegisterAdminHome) javax.rmi.PortableRemoteObject.narrow(
					obj,
					KiesRegisterAdminHome.class);
			KiesRegisterAdmin bean = home.create();
			KOALogHelper.log(
				LogHelper.INFO,
				"[RemoveKiesRegisterJob] start removing kiesregister");
			bean.removeImport(iTempDataID);
			KOALogHelper.log(
				LogHelper.INFO,
				"[RemoveKiesRegisterJob] finished removing kiesregister");
		}
		catch (NamingException ne)
		{
			String[] params = { "KiesRegisterAdmin" };
			KOALogHelper.logErrorCode(
				"RemoveKiesRegisterJob.execute",
				ErrorConstants.ERR_NAMING,
				params,
				ne);
			throw new KOADataBeheerException(
				KOADataBeheerException.EJBEXCEPTION,
				ne);
		}
		catch (RemoteException re)
		{
			String[] params = { "KiesRegisterAdmin" };
			KOALogHelper.logErrorCode(
				"RemoveKiesRegisterJob.execute",
				ErrorConstants.ERR_REMOTE,
				params,
				re);
			throw new KOADataBeheerException(
				KOADataBeheerException.EJBEXCEPTION,
				re);
		}
		catch (CreateException ce)
		{
			String[] params = { "KiesRegisterAdmin" };
			KOALogHelper.logErrorCode(
				"RemoveKiesRegisterJob.execute",
				ErrorConstants.ERR_CREATE,
				params,
				ce);
			throw new KOADataBeheerException(
				KOADataBeheerException.EJBEXCEPTION,
				ce);
		}
		return bResult;
	}
	/**
	 * Initialize the job.
	 * Always returns true, as no initialization is needed.
	 * 
	 * @throws EPlatformException	needed to fulfill abstract method signature
	 * 
	 */
	public boolean init() throws EPlatformException
	{
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[RemoveKiesRegisterJob.init] initialize...");
		return true;
	}
}