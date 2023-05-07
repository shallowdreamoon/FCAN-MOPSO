package PXY;

public class Constants {
	// Wechat App Parameters
	public static String OPENID_URL = "https://api.weixin.qq.com/sns/jscode2session";
	public static String APP_ID = "wxda48269baa2ca005";
	public static String APP_SECRET = "9eb646e4dbaf02b2b6e18d66422d4ad3";
	
	// Status
	public static String UPLOADING_STATUS = "UPLOADING";
	public static String UPLOAD_FAILED_STATUS = "UPLOAD_FAILED";
	public static String FAILED_STATUS = "FAILED";
	public static String COMPLETED_STATUS = "COMPLETED";
	public static String NORMAL_STATUS = "NORMAL";
	public static String SUSPENDED_STATUS = "SUSPENDED";
	public static String CHECKING_STATUS = "CHECKING";
	public static String UNQUALIFIED_STATUS = "UNQUALIFIED";
	public static String COLLECTING_STATUS = "COLLECTING";
	public static String PENDING_TO_APPROVE_STATUS = "PENDING_TO_APPROVE";
	public static String APPROVED_STATUS = "APPROVED";
	public static final String REJECTED_STATUS = "REJECTED";
	public static String LOST_STATUS = "LOST";
	public static String RECEIVED_STATUS = "RECEIVED";
	public static String PENDING_TO_RECHARGE_STATUS = "RECHARGING";
	public static String ASSIGNED_STATUS = "ASSIGNED";
	public static String APPROVED_WITH_MISSING_STATUS = "APPROVED_WITH_MISSING";
	public static final String PENDING_STATUS = "PENDING";
	public static String NORMAL_STATUS_IN_CHINESE = "正常";
	public static String ABNORMAL_STATUS_IN_CHINESE = "异常";
	public static String SUSPENDED_STATUS_IN_CHINESE = "异常";
	public static String UNQUALIFIED_STATUS_IN_CHINESE = "不合格";
	public static String KNOWLEDGE_NORMAL_STATUS_IN_CHINESE = "已审核";
	public static String CHECKING_STATUS_IN_CHINESE = "待审核";
	public static String PENDING_TO_APPROVE_STATUS_IN_CHINESE = "待审核";
	public static String ASSIGNED_STATUS_IN_CHINESE = "已发放";
	public static String LOST_STATUS_IN_CHINESE = "已遗失";
	public static String PENDING_TO_RECHARGE_STATUS_IN_CHINESE = "待充值";
	public static String RECEIVED_STATUS_IN_CHINESE = "已回收";
	public static String APPROVED_STATUS_IN_CHINESE = "审核通过";
	public static String COLLECTING_STATUS_IN_CHINESE = "回收中";
	public static String APPROVED_WITH_MISSING_STATUS_IN_CHINESE = "回单已回收，部分遗失";
	public static final String REJECTED_STATUS_IN_CHINESE = "审核不通过";
	public static Byte YES_STATUS = 1;
	public static Byte NO_STATUS = 0;
	
	// Order Status
	public static final int OrderStatus_UnDispatched = 1;
	public static final int OrderStatus_Dispatched = 2;
	public static final int OrderStatus_ArriveAtFactory = 3;
	public static final int OrderStatus_StartToLoad = 4;
	public static final int OrderStatus_SignedContract = 5;
	public static final int OrderStatus_ArriveAtCustomer = 6;
	public static final int OrderStatus_StartToUnload = 7;
	public static final int OrderStatus_FinishUnload = 8;
	public static final int OrderStatus_Suspended = 9;
	public static final int OrderStatus_ReadyToPay = 10;
	public static final int OrderStatus_UrgentToApprove = 11;
	public static final int OrderStatus_UrgentToPay = 12;
	public static final int OrderStatus_ExportedToPay = 13;
	public static final int OrderStatus_FailedToPay = 14;
	public static final int OrderStatus_NotPaid = 15;
	public static final int OrderStatus_Paid = 16;
	public static final int OrderStatus_PBIReadyToPay = 17;
	public static final int OrderStatus_ViewSignedContractToday = 9999;
	public static final int OrderStatus_PendingToProcessForDispatcher = 9998;
	public static final int OrderStatus_PendingToProcessForInformationer = 9997;
	public static final int OrderStatus_PendingToProcessForSiteSupervisor = 9996;
	public static final int OrderStatus_ReceiptRecordBeforeToApproveForInformationer = 9995;
	public static final int OrderStatus_PendingToProcessOfSiteSupervisorForPIC = 9994;
	public static final int OrderStatus_PendingToProcessForPIC = 9993;
	public static final int OrderStatus_DispathcedForInformationer = 9992;
	public static final int OrderStatus_UndispatchedToMerge = 9991;
	public static final int OrderStatusSeq_ReadyToPay = 10;
	public static String Dispatched_Name = "已调度未签协议";
	
	
	// Common Message Constants
	public static String MSG_DATABASE_FAILURE = "数据库操作失败，请稍后再试";
	public static String MSG_USER_NOT_ALLOWED = "用户无此权限，请与管理员联系";
	public static String MSG_PAGE_REFRESH = "请等待页面刷新";
	public static String MSG_ACCOUNT_ABNORMAL = "账号异常，请与管理员联系";
	public static final String MSG_RECORD_NOT_EXISTED = "无相关记录";
	
	// Login Controller Setting
	public static int VERI_CODE_DURATION_IN_MILLISECONDS = 2 * 60 * 1000;
	public static int VERI_CODE_EXPIRY_IN_MIN = 30;
	public static String ACCOUNT_UNBINDED_STATUS = "UNBINDED";
	public static final int CarrierAccountType = 4;
	public static final int CustomerAccountType = 2;
	public static final int DriverAccountType = 3;
	public static final int StaffAccountType = 1;
	
	// Login Message Constants
	public static String MSG_MOBILE_NOT_EXISTED = "手机号不存在，请与管理员联系";
	public static String MSG_MOBILE_DUPLICATE = "手机号重复，请与管理员联系";
	public static String MSG_VERI_CODE_NOT_EXISTED = "验证码不存在";
	public static String MSG_VERI_CODE_EXPIRED = "验证码已过期";
	public static final String MSG_VERI_CODE_FREQUENT_SENT = "请两分钟后再次获取验证码";
	public static final String MSG_VERI_CODE_SENT_FAILURE = "验证码发送失败，请稍后再试";
	public static final String UnauthorizedAccess = "未授权登录，请重新再试";
	public static final String MSG_Account_Abnormal = "账号异常，请与管理员联系";
	
	// Exam Controller Settings
	public static int NUM_HIST_EXAMS_PER_PAGE = 10;
	public static String CORRECT_ANSWER_DELIMITER = ",";
	public static String JSON_KEY_QUESTION = "questionId";
	public static String JSON_KEY_ANSWER = "content";
	public static String CLIENT_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
	public static final String Question_TrueFalseType = "判断";
	public static final String Question_SingleType = "单选";
	public static final String Question_MultiType = "多选";
	
	// Exam Message Constants
	public static String MSG_EXAM_SAVE_FAILURE = "考试保存失败，请与管理员联系";
	public static String MSG_EXAM_RECORD_NOT_EXISTED = "试卷记录不存在";
	public static String MSG_EXAM_EXPIRED = "已超过考试截止时间，无法保存成绩";
	public static String MSG_EXAM_SCORE_ERROR = "考试计分错误，请与管理员联系";
	
	// Knowledge Controller Settings
	public static int KNOWLEDGE_MAX_LENGTH= 20;
	public static int NUM_KNOWLEDGE_PER_PAGE = 20;
	public static int NUM_REPLY_PER_PAGE = 6;
	public static int NUM_CMT_PER_PAGE = 7;
	public static int NUM_KNOWLEDGE_SUBMISSIONS_PER_PAGE = 7;
	public static int LATEST_DAYS_LIMIT = 7;
	
	// Knowledge Message Constants
	public static String MSG_CMT_NOT_ALLOWED = "问题状态异常，请与管理员联系";
	
	//User CRUD Constants
	public static String MSG_USER_CREATED_FAILURE = "创建用户失败，用户已存在";
	public static String MSG_USER_CREATED_SUCCEED = "用户创建成功";

	public static String MSG_USER_UPDATED_SUCCEED = "更新成功！";
	public static String MSG_USER_UPDATED_FAILURE = "更新失败！";
	
	//Paper Score Export
	public static String TITLE_PAPER_SCORE = "员工考试成绩";
	
	
	//Knowledge Export
	public static String TITLE_KNOWLEDGE_RECORD = "知识点成绩";
	
	// Order Controller Settings
	public static final int NUM_ORDER_PER_PAGE = 5;
	public static final String OrderStatusInHistoryFlag = "in_history";
	public static final int DataTypeCarrier = 1;
	public static final int DataTypeDriver = 2;
	public static final int DataTypeGasCard = 3;
	public static final int DataTypeG7Card = 4;
	public static final int DataTypeSiteSupervisor = 5;
	public static final int NUM_INPUT_ITEMS_IN_ORDER_PER_PAGE = 20;
	public static final Object CarrierNotAvailable = "无";
	public static final String GasCardAmount = "金额：";
	public static final String MsgOrderVersionNotMatched = "订单版本不一致，请刷新再试";
	public static final String MsgOrderSaveError = "订单信息保存失败";
	public static final String MsgPriceNotMatched = "订单调度价格不一致";
	public static final String MsgMobileExisted = "手机号码已登记，请重新输入";
	public static final String DefaultPassword = "88888888";
	public static final String MSG_ADD_DRIVER_FAILURE = "司机信息添加失败";
	public static final String MSG_ADD_CARRIER_FAILURE = "承运商信息添加失败";
	public static final String MSG_ADD_DRIVER_SUCCESS = "司机信息添加成功";
	public static final String MSG_ADD_CARRIER_SUCCESS = "承运商信息添加成功";
	public static final int Role_Dispatcher = 5;
	public static final int Role_SiteSupervisor = 3;
	public static final int Role_PIC = 4;
	public static final int Role_Informationer = 2;
	public static final String ViewSignedContractToday_Name = "已签协议（当日）";
	public static final String PendingToProcess_Name = "待处理";
	public static final String PendingToProcessForInformationer_Name = "信息员";
	public static final String PendingToProcessForSiteSupervisor_Name = "现场监理";
	public static final String ReceiptsToReceive_Name = "卸货完毕";
	public static final String WarningType_IncompleteDriverInfo = "司机银行卡信息不完善";
	public static final String WarningType_ExtraFeeExisted = "存在其他费用";
	public static final String WarningType_UrgentToApprove = "加急审核";
	public static final String WarningType_GasCardToReceive = "油卡待回收";
	public static final String WarningType_G7CardToReceive = "G7卡待回收";
	public static final String WarningType_AllReceiptsReceived = "回单已回收";
	public static final String WarningType_HaveToCreateProcessRecord = "指派现场监理回收回单";
	public static final String WarningType_NoReceiptsToReceive = "不需要回收回单";
	public static final String WarningType_NotAllReceiptsReceived = "回单回收中";
	public static final String WarningType_AllReceiptsReceivedToApprove = "确认回单回收";
	public static final String WarningType_AbnormalCaseToProcess = "存在异常情况";
	public static final String WarningType_ContractToCollect = "合同待回收";
	public static final String WarningType_NullDriver = "未指派司机";
	public static final String WarningType_HasMergedAndNotSettledOrders = "存在未处理拼单";
	public static final int ReceivedCardType_Gas = 1;
	public static final int ReceivedCardType_G7 = 2;
	public static final String Table_DriverHasGasCard = "Driver_has_gas_card";
	public static final String Table_DriverHasG7Card = "Driver_has_g7_card";
	public static final String MSG_Order_ProjSeq_Duplicate = "运输计划添加失败，委托单号重复";
	public static final String IMPORTED_FROM_WX = "WX";
	public static final String MSG_Order_Add_Success = "订单（%s）添加成功";
	
	// Upload Image Controller
	public static final String Uploaded_Images_Folder = "/data/uploaded_images";
	public static final String IMAGE_NOT_EXISTED = "图片不存在";
	public static final String Cleared_Images_Folder = "/data/uploaded_images/to_del";
	public static final String NO_RIGHT_TO_OPER_IMAGE = "没有权限";
	
	// Carrier Controller
	public static final int CarrierOrderCountListType_Summary = 1;
	public static final int CarrierOrderCountListType_Assigned = 2;
	public static final int CarrierOrderCountListType_History = 3;
	public static final int CarrierOrderCountListType_Unpaid = 4;
	public static final int CarrierOrderCountListType_Paid = 5;
	public static final int CarrierOrderCountListType_NormallyUnpaid = 6;
	public static final int CarrierOrderCountListType_AbnormallyUnpaid = 7;
	public static final int CarrierOrderCountListType_NormallyUnpaidByDay = 8;
	public static final int CarrierOrderCountListType_PaidByDay = 9;
	public static final int CarrierOrderCountListType_FailedToPay = 10;
	public static final int CarrierOrderCountListType_ExportedToPay = 11;
	public static final int CarrierOrderCountListType_ExportedToPayByDay = 13;
	// Order_status_id from 1 to 9
	public static final int CarrierOrderCountListType_UnDispatched = 21;
	public static final int CarrierOrderCountListType_Dispatched = 22;
	public static final int CarrierOrderCountListType_ArriveAtFactory = 23;
	public static final int CarrierOrderCountListType_StartToLoad = 24;
	public static final int CarrierOrderCountListType_SignedContract = 25;
	public static final int CarrierOrderCountListType_ArriveAtCustomer = 26;
	public static final int CarrierOrderCountListType_StartToUnload = 27;
	public static final int CarrierOrderCountListType_FinishUnload = 28;
	public static final int CarrierOrderCountListType_Suspended = 29;
	
	public static final Object CarrierOrderCountListType_UnDispatchedChinese = "未调度";
	public static final Object CarrierOrderCountListType_DispatchedChinese = "已调度";
	public static final Object CarrierOrderCountListType_ArriveAtFactoryChinese = "已到厂";
	public static final Object CarrierOrderCountListType_StartToLoadChinese = "开始装货";
	public static final Object CarrierOrderCountListType_SignedContractChinese = "协议签订";
	public static final Object CarrierOrderCountListType_ArriveAtCustomerChinese = "已到达";
	public static final Object CarrierOrderCountListType_StartToUnloadChinese = "开始卸货";
	public static final Object CarrierOrderCountListType_FinishUnloadChinese = "卸货完毕";
	public static final Object CarrierOrderCountListType_SuspendedChinese = "待处理";
	public static final Object CarrierOrderCountListType_NormallyUnpaidChinese = "未付款";
	public static final Object CarrierOrderCountListType_ExportedToPayChinese = "已导出";
	public static final Object CarrierOrderCountListType_PaidChinese = "已付款";
	public static final int OrderConfirmedByCarrier_RcptsReceived = 1;
	public static final int OrderConfirmedByCarrier_Paid = 2;
	
	// Order Service
	public static final int UploadImageType_Abnormal = 1;
	
	// Driver Controller
	public static final String DriverOrderStatusUpdateFailure = "订单状态更新失败，请刷新再试";
	public static final int GascardType_Fixed = 1;
	
	// DriverHasGasCard Message
	public static final String MSG_GasCard_LOST = "油卡遗失";
	public static final String MSG_G7Card_LOST = "G7卡遗失";
	
	// System Parameter ids
	public static final int SysParamId_GasCardMissedPenalty = 1; 
	public static final int SysParamId_G7CardMissedPenalty = 2;
	public static final int SysParamId_ContractMissedPenalty = 3;
	public static final int SysParamId_DefaultPaymentPeriod = 4;
	public static final int SysParamId_DelayDaysPerDriverInfo = 5;
	public static final int SysParamId_DelayDaysPerDriverOperation = 6;
	public static final int SysParamId_CarrierUpdateDriverMax = 7;
	public static final int SysParamId_MonthesForPaymentStatsOfDriver = 8;
	public static final int SysParamId_DaysOfPaidOrdersForCarrier = 9;
	public static final int SysParamId_DaysOfPaidOrdersForDriver = 10;
	public static final int SysParamId_DriverHomeTitle = 11;
	public static final int SysParamId_DriverEditBankInfoMaxTimes = 12;
	public static final int SysParamId_ExamTrueFalseQuestionScore = 13;
	public static final int SysParamId_ExamSingleQuestionScore = 14;
	public static final int SysParamId_ExamMultipleQuestionScore = 15;
	public static final int SysParamId_AbnormalDaysOfOilCard = 16;
	public static final int SysParamId_SpeedPerHour = 17;
	public static final int SysParamId_MinimumPaymentRatio = 18;
	
	// Missed Contract
	public static final String MSG_CONTRACT_MISSED = "合同遗失";
	public static final String Order_Import_History_NotExisted = "导入记录不存在";
	public static final String ALL_RECEIPTS_RECEIVED_WITH_MISSING_DESC = "ALL_RECEIPTS_RECEIVED_WITH_MISSING";
	public static final String G7Card_LOST_DESC = "G7Card_LOST";
	public static final String GasCard_LOST_DESC = "GasCard_LOST";
	public static final String Contract_LOST_DESC = "Contract_LOST";
	public static final String Carrier_Insurance_DESC = "Carrier_Insurance";
	public static final String MSG_Carrier_Insurance = "保险费";
	public static final String MSG_Other_Extra_Fee = "其他";
	public static final String GasCard_ASSIGNED_DESC = "GasCard_ASSIGNED";
	public static final String MSG_GasCard_ASSIGNED = "油卡面值";
	
	// parameters used for merging orders
	public static final String DefaultDelimiter = ",";
	public static final String MSG_Input_Error = "输入错误，请重新检查";
	public static final String PaymentSequencePrefix = "ZF-";
	public static final int PaymentSequenceMaxDigits = 3;
	public static final String MsgSuccefullyMerged = "订单合并成功";
	
	// parameters used for guanchebao
	public static final String username4guanchebao = "武汉久凌";
	public static final String password4guanchebao = "Wuhan@90";
	public static final String username4guanchebaotest = "log56";
	public static final String password4guanchebaotest = "111111";
	// 以前的订单需要使用log56账号，所以需要判断建单的时间
	public static final String DateFlagString = "2020-9-29 20:01:59";

	
	public static final String systemFixedValue = "abcJH76fh*21179Ft2d";

	//public static final String lugeUrl = "http://220.248.226.76:30242/tax_settle/entService";
	public static final String payment_Application_id = "4b2fb5c69b18ac4a2c084efd9e91a11df4a4c33c";
	public static final String privatekey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCBJqjWzSUbDaahS3MBcd/SURpDwPtERrllzSyLPEgW72ECHZYNIXLUNt8HlYbNMeBTD7OxZPApr6OmpbQ0gUzlvPcAcLBol1OT5dcSqqaiz3xjDOkJX+rohvVDjzn6RM8WKo5tonG2Msx40SEuZnu+ueEmCJtccshfUZloVzxRnPize3JS/QzOOUI679ESpaWefYfTuic2AOOPjT7q0wZKA4iDkTqf4abFjDgjCGs+Nv948G0aPPcIC8FhG41x2gStQtQXi4lS16KwTgAcLhIOM/E1NCqnqPAnp4RLtZNklszj4hBB+tfRzy0wfdxTtSvo6Q0cn3wLH4hMP6VKiIXxAgMBAAECggEAJ4QAnNhfxgtDNI61PXfuF5ehBaICPz20dCV2v5NOK1ZKxC7xPktBqxSu4M8rU5UzIbboMBrGMLsAw3hnzrjRImnS2VuhXh8O/22PhLKaEa6STGwi0S973HGOkYE9KAntosaalYjiafDZBw5VrLDGo/CTpuNzRbIJBMEewwIN/potTw771q7KpcOuz0il2ugwq8NFby8Hjvrz1ntlf4DM0R5hIpFh5tGrccL8fvAxbdHyd9WpdzcCLAyCTiFs5vFYH78ZxdzkVZ+6+PrtGxkLmSY8KWMQWWHal4IovxPdVW113FPxz6qyAropJXZ7JdRpj5nDyiIKq97HgROvlY9aEQKBgQC9xdBHM9/92eUKvcYK/g5B6dgsWuIFJloW2ru4WEmxMdXXvjYsUSMkzFFAKKBELaziOhxeHnm3AM29PWQr4vrPyoi64LN6BvWNMqyv1HFl12ttyFoB83un4zXMpq2rJvI0BauaPWQloSbIp2/Jem6KlWU2wyVlc6dw6vffAi8eawKBgQCuOPAfjx8ViPS2tefh6vIKCD5j+Av7es9CjRkSNEm/5w/juVKfsYN/BNe3WoGueByfV4sRHfjAOBRQtOdliWEICOJR8AfhtluDojiZYfVFl4hBCupGKfX6EOhk83ZcP8CKPW4Y00BR6QIek/z1U/Hq3OT5llNeeEyxMkH+2WTMEwKBgFp7CV4Zhq+TB1iCuP0euo+EvjQPtPn9mjCRY7V2KtSYTTwLDes9WENAQ3ESra80E9zdFg9t+lxdvrp+ndcq5qjSCTUI06nXzZXf1mQTJ2oGNstmHjwV6hxXAZysz9rtu8c/+NyWbrxeRI6g/nSSskfTCSn+C0N+zBPThBFJCkw3AoGAKlhqfLEzs+9C03npL28Ef027pF6wwuH5qEpVMqiE2GDX6tirbWce9+t8ZvNXm8lT46LKiVGImWfIAEnICFTYwbQvFg1KTSEeoyL1sMovbEfFdVGq2XzvbWVYptjKBufz62W88cm+02nX+wirLhHGNfiUqtlP0CFbOqJligTazAMCgYEAufOpJjtEEthmb1bGnwyZeU9FTgUp8nwBJzXxJ/hBQbangC6PgB02p6MNMA/tmys49OH5My1LFCwRmNBjfJafizigl9PlBWgga0cScgRFAHcBAlNSuBMeg//phDgn1OMNBqS4potyCoz+R5Taubf3k5FGuH6wE0NjAVq1boO4ASU=";
	public static final String privatekeytest ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCkK0xVYrM7jmE4198EK+EriYEJiiLH45BFKww94HFz4gzeFZlkCpWdgpZyX8alGv94OSUTtJKK+kbPJAYAMwK/Ffyc39Hv8dNhHR0C3Fy0HY7dgNi+Ycd+43IwuCkPgDk1rtJiS9XAX14AVccQ3s9NtKEzggvQwgQDOXaisF4xpbJ+sHpnx4eaE6lMMbdEqlv1kigb5WjynEWjUIfcuznQYY6ZPnf0myw4Gw/W0HLHtMUqgIZB9dQM6R79e/fOWS47m3d+A3P0IywiXu5vi02Z7802KIX3yrLnzDGW1PCEn/3F3a2QbLaP3CosF4SzRyFfrCbraQVfvMxiSq2fqle/AgMBAAECggEAGdGhcLBUJeKvkaQJs/s+r30xqRFrhOpaLzak9zpMPYBoq6jT9fLgMuRtytEbSa4kTYEXPrEgdvEDn+I3e2KKZQkNMJLWzpjGuLg+bsu/XKhlZpqz+8Ue/erBc74S3xg4ZyP2fHUWyel1vDHBW/reuYbV72bdeth4jxVDfjSkq8P1yjNPm3K6zSxNFd1um81q55BKYfC9Zz4KaqORknGpLS3NnfdbKPHIUSnwpmnMXUZEstnX2MgTSFbTW1LIEEtn+n3V/480/jKcRKtfllsH1aLfbmSwmxhUh+gNudKpnpk3827gq5ea4tnAlsFFgE1bcEdC1lfwX91AERHHXODkyQKBgQDeiPbBK7jGmg+kvEwz3r11FJ7HaE1TznAX7uBphvqlsjEi2Yd8buMrcq+vfFPycH41hQk79xfKf6QgpDW9nVeif8uUeY/okaoNkDkP+kCMbQsLkEpPNPkVumvMu9Lnp8S6T5nxIXemBOH8TYjwAof6gHpOdBEPvPdkzV2U9zaS3QKBgQC822UueTlYEGfoZGsHIV+PGn6LJKBeqSQ21AG8RrhEd9QBcz2l8yEb2GnqZDYkalKBA5yHc6Cpi23IxUOo0NLYNfeqDiIDnOaXbFRPB0V7/IR1to5JcbN+0ekPGD9fSmsu4V48L5CvN+FH9gHLqpcrgCyHCKxdQ0FlI8U4rpsFSwKBgHvXzTsDkfk0k7Ah+maDdvxJVXOz4BTbthYbolgX/jVoTWAPnw7oxxPO52bJ0g4B/LrcJp9wi5TuI/DmceHsRnDwneMrI6NSLUkPEjKKGpILvTHaQwCBgn1NGeD+RgkVnK3yzsEa1vNVlE5M+ABZ6ELIbUhr33LavvMiNwpH6d8hAoGBAJzZollMuQGY7pOmAvXzE8hoerzZeI/l7nvS7HYQTi4fT2a87XCMUu0rehv/irAH1enss+4TbFYjxNRAcYcdmF+VwtHr1vglMaaFhCf2pBDrkp+pNNdupWMot2Wf/9IGnqpa5scZwhKkWGhJuwgJ8G9ijpi/7Vev6fo0ETKYYYutAoGADXH7RVBFX2NT2/hBYQGX0ljie2Vh2XI3M/kWNzJB7vPBQ9Mj/a9AkpXu6u9Z4tV6CAAuCOSGLwS6qFkrUIvbTIqlHVECS0j/mWLgzbEy99Cq8m9Ky0RfiDWeAKxw9vEYb4Mhw/tDfXSMB6WpFITOZHbhRXXRBgp/2nJFwEEeTEM=";
	//public static final String privatekey2="MIIEowIBAAKCAQEApCtMVWKzO45hONffBCvhK4mBCYoix+OQRSsMPeBxc+IM3hWZZAqVnYKWcl/GpRr/eDklE7SSivpGzyQGADMCvxX8nN/R7/HTYR0dAtxctB2O3YDYvmHHfuNyMLgpD4A5Na7SYkvVwF9eAFXHEN7PTbShM4IL0MIEAzl2orBeMaWyfrB6Z8eHmhOpTDG3RKpb9ZIoG+Vo8pxFo1CH3Ls50GGOmT539JssOBsP1tByx7TFKoCGQfXUDOke/Xv3zlkuO5t3fgNz9CMsIl7ub4tNme/NNiiF98qy58wxltTwhJ/9xd2tkGy2j9wqLBeEs0chX6wm62kFX7zMYkqtn6pXvwIDAQABAoIBABnRoXCwVCXir5GkCbP7Pq99MakRa4TqWi82pPc6TD2AaKuo0/Xy4DLkbcrRG0muJE2BFz6xIHbxA5/iN3tiimUJDTCS1s6Yxri4Pm7Lv1yoZWaas/vFHv3qwXO+Et8YOGcj9nx1FsnpdbwxwVv63rmG1e9m3XrYeI8VQ340pKvD9cozT5tyus0sTRXdbpvNaueQSmHwvWc+CmqjkZJxqS0tzZ33WyjxyFEp8KZpzF1GRLLZ19jIE0hW01tSyBBLZ/p91f+PNP4ynESrX5ZbB9Wi325ksJsYVIfoDbnSqZ6ZN/Nu4KuXmuLZwJbBRYBNW3BHQtZX8F/dQBERx1zg5MkCgYEA3oj2wSu4xpoPpLxMM969dRSex2hNU85wF+7gaYb6pbIxItmHfG7jK3Kvr3xT8nB+NYUJO/cXyn+kIKQ1vZ1Xon/LlHmP6JGqDZA5D/pAjG0LC5BKTzT5FbprzLvS56fEuk+Z8SF3pgTh/E2I8AKH+oB6TnQRD7z3ZM1dlPc2kt0CgYEAvNtlLnk5WBBn6GRrByFfjxp+iySgXqkkNtQBvEa4RHfUAXM9pfMhG9hp6mQ2JGpSgQOch3OgqYttyMVDqNDS2DX3qg4iA5zml2xUTwdFe/yEdbaOSXGzftHpDxg/X0prLuFePC+QrzfhR/YBy6qXK4AshwisXUNBZSPFOK6bBUsCgYB71807A5H5NJOwIfpmg3b8SVVzs+AU27YWG6JYF/41aE1gD58O6McTzudmydIOAfy63CafcIuU7iPw5nHh7EZw8J3jKyOjUi1JDxIyihqSC70x2kMAgYJ9TRng/kYJFZyt8s7BGtbzVZROTPgAWehCyG1Ia99y2r7zIjcKR+nfIQKBgQCc2aJZTLkBmO6TpgL18xPIaHq82XiP5e570ux2EE4uH09mvO1wjFLtK3ob/4qwB9Xp7LPuE2xWI8TUQHGHHZhflcLR69b4JTGmhYQn9qQQ65KfqTTXbqVjKLdln//SBp6qWubHGcISpFhoSbsICfBvYo6Yv+1Xr+n6NBEymGGLrQKBgA1x+0VQRV9jU9v4QWEBl9JY4ntlYdlyNzP5FjcyQe7zwUPTI/2vQJKV7urvWeLVeggALgjkhi8EuqhZK1CL20yKpR1RAktI/5li4M2xMvfQqvJvSstEX4g1ngCscPbxGG+DIcP7Q310jAelqRSEzmR24UV10QYKf9pyRcBBHkxD";
	public static final String KEY_A = "129856@%DWEWQEQ(rre3453)(#$118bbbfsdfs!~~~```";
	public static final String KEY_B = "129856%DWEWQewqw!#!$%$^%^&^&^*^&*^8bbbfsdfs!!~~~```";
	public static final String PARTNER_CODE = "WHJL20190816001";
	public static final int speedPerHour = 60;
	
	public static final String privatekey90 = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDNnAhth2IEhLd3Z2wGS1xmgzicXh1l7XALgn+v39ImCzyxz9j/rBDELEq1xxNuf0nz7nglPiY2STlqgYKxgpkkg3pMiyoVF31MNzP84FL9/DZhMEyisVXrNfcM5SGWDXVoPYR4HUquCSCz0tbN9JHa7uI+gPwDRkl9Yad3+FVF4hJfzgMRpoqTWZ69S+ApsioGxAH9w5RNoJ1Hat4aOI8uXMQ2kiLass/CFkaXwf22VEDsuB+adymAYfTLdGIr9LlnCvxVj7BAsBzoHpSRzq6mGQyyAy578RC4OGGpFqe5EltWpcaRJt4MWPIH0fPgpCnQraRPmFyzkXa6WM8/ydFTAgMBAAECggEAMG8HFTYFAud4iGoWak0qUQOsPDl3V2FdOXjeN0HUMR4IiCJcDa4srOl6F+PzUd4uP4X3S16ehb8uBUbI0KPhC1blYk6N9TX5UBDY8EKviiD1dmN4SgLYKHYn4iO/i6dT9uA26kyMIP8JcxLBgvaPvTD0nqCnvOW7vgtY9cvL2pAAiNwN+rClMEYUss7lbGOc0c1RTWekohTk9fK10L9BMJsLr3PPotmXeEdtgudoNq8El75oaCGUVh7DKr5YoP2gR5MzvK3mYbhGGDLkyZ+iLjXONsr6jNH70c1gJ8lMJJe2SBlygOg6NPbfSIMU0jGPNOnUQ/218mr3ZkaN3MDWUQKBgQDmjUueeCgy4E6T7PiBki/hMwYcX0k91fPHs9ZPIEcLO2Bfk0Oz9qlFgtI1ZS5niQSaMQRWy2UzHFGvpzDxfBjCj4ait6nXisN1SD/TPsyF8K4KuYtr4xGWit6d+PLKdoGjalvUgog67QCRb4a5XBu12EDIcGU5GQ3bofwuufZS1wKBgQDkTfB8kMLDiVVyvOXSamMwXHH/T1SpauGdlIOudXcJ1pfEFArTNCSyYPrPMaH2MBwXg7QMF2+tw8JgpMCdb/q4X1X/NhCDHkiXTK0XR3XGcZAUPAnDVILexRfuyH4B+bRStPDrv/k5q+FYJebqb7t4eRaC07mftF4ZbtLTE9wh5QKBgQDbJLcaHDBpm5hVN0xHLQNh1bQbR99AbZvY2PH+KDofTQMgFcSmzPC858qTgmeHdk/vmGP9dDvvRx3GDu+Qulp2pSJejKFeBcIgOtqgGvMg5VMpUpBZrm5LYBiWo8lz9QzonfJHnwOKrzh5BT9T9V9JjBKX823jxhLMD767U3v15QKBgQCkVAU5Y+kMXZmHkLfLkI/8i03yeuNxXmEuz2tI83vPNQrgM5znEK0pR+y/nfX6Af72XfIGkgfshu8uKOgLakKI2vZHUF7qQa32WVZtXx5CzBLsvZo5YmcgebUURnOMcHN+5IZU6kU1YaY9kGp5bBsASAgP+t2tOmJQ5BgTnkPgkQKBgQDP9sVyUMmFIy539Avcbw22P8pyvfnVxU60E9pUWoPuJEjeCvJgWEkyRscETgqPoZGFKHyTS7HUXz1aM3WYdovABxxRSQA6b5hkTZ6Cx9rims5xVig9vb2setx/mB4Zncmjk3dySZrCW/7jzSSgO3ZgOEaKLLOxJ1YW1qBZOI0CEg==";
	public static final String publickey90 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzZwIbYdiBIS3d2dsBktcZoM4nF4dZe1wC4J/r9/SJgs8sc/Y/6wQxCxKtccTbn9J8+54JT4mNkk5aoGCsYKZJIN6TIsqFRd9TDcz/OBS/fw2YTBMorFV6zX3DOUhlg11aD2EeB1Krgkgs9LWzfSR2u7iPoD8A0ZJfWGnd/hVReISX84DEaaKk1mevUvgKbIqBsQB/cOUTaCdR2reGjiPLlzENpIi2rLPwhZGl8H9tlRA7LgfmncpgGH0y3RiK/S5Zwr8VY+wQLAc6B6Ukc6uphkMsgMue/EQuDhhqRanuRJbVqXGkSbeDFjyB9Hz4KQp0K2kT5hcs5F2uljPP8nRUwIDAQAB" ;
	public static final String publickeytest = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhpsfkw63S5xh8xmqvC2AW4Q9kT5HINVK7qvH9Zy/doSlqZMOgLKpBzUsPSeXkzO0p2OdNvmsHYVIiAmQUGT4CBqr33fr2T1EtaMMkQ1ROaw19hLfrys7YXlCisZhTq1u7w9cZoGIs3jQbuhhayQLNt/rGQB61jHw5dvs7hPdf+Q3PZnasTELIsveaKzG2nz9yIbRpT0JL/Lfpr4BbHJMR4ZDQBSzEHqvjztTjMI+FggC0he5fpFhsL3EfltNevTEftYg3+il5Y85FHG6dTSUn5iaC5aeNjx5ii28rg6ByQbHigNSYTZP7j/llT17gSIy8KAXiLBthZX4MuMlVIy38QIDAQAB" ;

	// 路歌接口地址
	// 运单基础操作接口 & 获取轨迹接口
	public static final String basicOperationURL = "http://sap.log56.com/HR_SAP/CisServlet";
	
	// 运单终结接口 & 申请支付接口 & 申请支付结果查询接口
	public static final String orderOperationURL = "http://syf.log56.com/tax_settle/entService";
	
	// 回单上传接口
	public static final String receiptUploadURL = "http://evalua.log56.com/eva/entServer";
	
	// 查询电子油卡卡号接口
	public static final String queryGasCardNumber = "http://syf.log56.com/tax_settle/entService";
	//public static final String queryGasCardNumber = "http://220.248.226.76:30242/tax_settle/entService";
}
