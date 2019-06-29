package com.xinnet.smart.api;

public interface ManagerApiDeclarations {
	String BACKUP_CALL_PLATFORM_ACTION = "/job/backup/autoByWeek.job";
	String BACKUP_CALLBACK_DELETE_ACTION = "/backup/callback/delete.do";
	String BACKUP_CALLBACK_RECOVER_ACTION = "/backup/callback/recover.do";
	String BACKUP_CALLBACK_CREATER_ACTION = "/backup/callback/manual.do";
	String BACKUP_CALLBACK_MANUALCREATE_ACTION = "backup/callback/create";
	String BACKUP_CALLBACK_BACKUPDELETE_ACTION = "backup/callback/delete";
	String BACKUP_CALLBACK_BACKUPRMRFDELETE_ACTION = "backup/callback/backupRmRfDelete";
}
