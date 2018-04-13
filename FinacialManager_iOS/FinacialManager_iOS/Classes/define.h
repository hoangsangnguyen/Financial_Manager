//
//  define.h
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#define ServerURL @"http://sixfinancialboxs.azurewebsites.net/api"

// Method Index
#define NUM_METHOD          5

#define METHOD_GET          (0*NUM_METHOD+0)
#define METHOD_GET_2        (1*NUM_METHOD+0)

#define METHOD_POST         (0*NUM_METHOD+1)
#define METHOD_POST_2       (1*NUM_METHOD+1)
#define METHOD_POST_3       (2*NUM_METHOD+1)
#define METHOD_POST_4       (3*NUM_METHOD+1)
#define METHOD_POST_5       (4*NUM_METHOD+1)
#define METHOD_POST_6       (5*NUM_METHOD+1)
#define METHOD_SAVEDATA     (6*NUM_METHOD+1)

#define METHOD_PUT          (0*NUM_METHOD+2)
#define METHOD_PUT_2        (1*NUM_METHOD+2)
#define METHOD_PUT_3        (2*NUM_METHOD+2)
#define METHOD_PUT_4        (3*NUM_METHOD+2)
#define METHOD_PUT_5        (4*NUM_METHOD+2)
#define METHOD_PUT_6        (5*NUM_METHOD+2)
#define METHOD_PUT_7        (6*NUM_METHOD+2)
//#define METHOD_UPDATE       (0*NUM_METHOD+3)
//#define METHOD_UPDATE_2     (1*NUM_METHOD+3)


#define METHOD_DELETE       (0*NUM_METHOD+4)
#define METHOD_DELETE_2     (1*NUM_METHOD+4)
#define METHOD_DELETE_3     (2*NUM_METHOD+4)
#define METHOD_DELETE_4     (3*NUM_METHOD+4)

#define arrMethods          @[@"GET", @"POST", @"PUT", @"UPDATE", @"DELETE"]

// Application Delegate
#define AppShare [UIApplication sharedApplication]
#define App ((AppDelegate*)AppShare.delegate)
#define AppNav [SlideNavigationController sharedInstance]

#define Config  App.configure
#define Socket  App.socket

#define KEY_INFO_DEVICE @"DIC_INFOR_CURRENT_DEVICE"

#define SECOND_TK_KEY    @"media-type"
#define SECOND_TK_VALUE  @"application/json"

#define TK_RPREFIX       @"aaaaaaaa"
#define TK_RSUFFIX       @"bbbbbbbb"

#define TK_SPREFIX       @"cccc"
#define TK_SSUFFIX       @"dddd"

#define MAINCOLOR RGB(0x009DF7)


#define IOS_8_OR_LATER() ([[[UIDevice currentDevice] systemVersion] floatValue] >= 8.0)
#define IOS_9_OR_LATER() ([[[UIDevice currentDevice] systemVersion] floatValue] >= 9.0)
#define IOS_10_OR_LATER() ([[[UIDevice currentDevice] systemVersion] floatValue] >= 10.0)

#define IS_IPAD()   (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad)

#define IS_IPHONE_4_LANDSCAPE (fabs((double)[[UIScreen mainScreen]bounds].size.height - (double)480) < DBL_EPSILON)
#define IS_IPHONE_5_LANDSCAPE (fabs((double)[[UIScreen mainScreen]bounds].size.height - (double)568) < DBL_EPSILON)
#define IS_IPHONE_6_LANDSCAPE (fabs((double)[[UIScreen mainScreen]bounds].size.height - (double)667) < DBL_EPSILON)
#define IS_IPHONE_6P_LANDSCAPE (fabs((double)[[UIScreen mainScreen]bounds].size.height - (double)736) < DBL_EPSILON)


#define IS_Landscape()            (SWIDTH > SHEIGHT)
#define IS_Portrait()             (SWIDTH < SHEIGHT)

#define IS_IPHONE_4()        (MAX(SWIDTH, SHEIGHT) == 480)
#define IS_IPHONE_5()          (MAX(SWIDTH, SHEIGHT) == 568)
#define IS_IPHONE_6()        (MAX(SWIDTH, SHEIGHT) == 667)
#define IS_IPHONE_6P()        (MAX(SWIDTH, SHEIGHT) == 736)



#define VCFromSB(VC, SBName) (VC*)[[UIStoryboard storyboardWithName:SBName bundle:nil] instantiateViewControllerWithIdentifier:NSStringFromClass([VC class])];

#define VFromXIB(CLASS_NAME,INDEX) [[[NSBundle mainBundle] loadNibNamed:CLASS_NAME owner:self options:nil] objectAtIndex:INDEX];

#define GET_TABLE_CELL(CellCls) CellCls *cell = [tableView dequeueReusableCellWithIdentifier:@#CellCls forIndexPath:indexPath]

#define OBJ_FROM_NIB(name) [[NSBundle mainBundle] loadNibNamed:@#name owner:nil options:nil].firstObject


//Key save test
#define KEY_TEST(currentSubTest)     SF(@"test%ld",currentSubTest)

// Check Empty
#define isEmpty(A)      ((!A) || [A length] <= 0)
#define E(A)            ((A) ? (A) : @"")

#define ESCAPE(A)   [A stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]
#define UNESCAPE(A) [A stringByRemovingPercentEncoding]


//Encode, Decode NSUserdefault
#define DEC(A)  _##A = [aDecoder decodeObjectForKey:@#A]
#define DECB(A) _##A = [aDecoder decodeBoolForKey:@#A]
#define DECI(A) _##A = [aDecoder decodeIntegerForKey:@#A]
#define DECD(A) _##A = [aDecoder decodeDoubleForKey:@#A]

#define ENC(A) if(_##A) [aCoder encodeObject:_##A forKey:@#A]
#define ENCB(A) [aCoder encodeBool:_##A forKey:@#A]
#define ENCI(A) [aCoder encodeInteger:_##A forKey:@#A]
#define ENCD(A) [aCoder encodeDouble:_##A forKey:@#A]

// API Parse Server Data
#define MAP(A)      _##A = [dic objectForKey:@#A];
#define MAPB(A)     _##A = [[dic objectForKey:@#A] boolValue];
#define MAPI(A)     _##A = [[dic objectForKey:@#A] integerValue];
#define MAPF(A)     _##A = [[dic objectForKey:@#A] floatValue];
#define MAPD(A)     _##A = [[dic objectForKey:@#A] doubleValue];
#define MAPL(A)     _##A = [[dic objectForKey:@#A] longlongValue];

#define MAPDate(KeyName, DateFormatter)   MAPRDate(KeyName, KeyName, DateFormatter)
#define MAPRDate(AssignedObj, KeyName, DateFormatter)   _##AssignedObj = [DateFormatter dateFromString:[dic objectForKey:@#KeyName]]

#define MAPO(A, CLASS)      _##A = [[CLASS alloc] initWithData:[dic objectForKey:@#A]]
#define MAPA(A, CLASS)      _##A = [[NSMutableArray alloc] init]; \
NSArray *a##A = [dic objectForKey:@#A]; \
if(a##A && [a##A isKindOfClass:[NSArray class]]) { \
NSInteger count = a##A.count; \
for(NSInteger i=0; i<count; i++) { \
id item = a##A[i]; \
if([item isKindOfClass:[NSDictionary class]]) { \
[_##A addObject:[[CLASS alloc] initWithData:item]]; \
} \
} \
}

#define MAPAO_ARR(A, CLASS)      if (_##A == nil) { \
_##A = [NSMutableArray array]; \
} \
if (dic && [dic isKindOfClass:[NSArray class]]) { \
for (id obj in dic) { \
CLASS *dic = [[CLASS alloc] init]; \
[dic mapDataFromServer:obj]; \
[_##A addObject:dic]; \
} \
}else{\
NSString *str = VarName(_##A);\
NSLog(@"Please get dic for key %@",str);\
}\

#define MAPAO_DIC(A, CLASS)      if ([dic objectForKey:@#A]) {\
NSDictionary *dicB = [dic objectForKey:@#A];\
if (_##A == nil) {\
_##A = [NSMutableArray array];\
}\
if (dicB && [dicB isKindOfClass:[NSArray class]]) {\
for (id obj in dicB) {\
CLASS *aa = [[CLASS alloc] init];\
[aa mapDataFromServer:obj];\
[_##A addObject:aa];\
}\
}\
}\

// Merge
#define M(A)        _##A = obj.A;
#define MX(A)       [_##A mergeFrom:obj.A];

//Get Variable Name
#define VarName(arg) (@""#arg)

//fast dictionary
#define KEYVALO(KEY) KEYVALMO(KEY, self.KEY)
#define KEYVALN(KEY) KEYVALMN(KEY, self.KEY)

#define KEYVALMO(KEY, VAL) @#KEY: E(VAL)
#define KEYVALMN(KEY, VAL) @#KEY: @(VAL)


// Screen size
#define SRECT [UIScreen mainScreen].bounds
#define SWIDTH SRECT.size.width
#define SHEIGHT SRECT.size.height

#pragma mark - Alert
//Alert
#define ALERT(CONTENT_FORMAT, ...) MSG_NEW(nil, CONTENT_FORMAT, ##__VA_ARGS__);

#define MSG(TITLE, CONTENT_FORMAT, ...)    \
[[[UIAlertView alloc] initWithTitle:TITLE message:[NSString stringWithFormat:CONTENT_FORMAT, ##__VA_ARGS__] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil] show];

#define MSG_NEW(TITLE, CONTENT_FORMAT, ...) \
UIAlertAction *cancelAction = [UIAlertAction actionWithTitle:@"OK" style:UIAlertActionStyleDefault handler:nil];\
UIAlertController *alert = [UIAlertController alertControllerWithTitle:nil message:[NSString stringWithFormat:CONTENT_FORMAT, ##__VA_ARGS__] preferredStyle:UIAlertControllerStyleAlert];\
[alert addAction:cancelAction];\
if ([self isKindOfClass:[UIViewController class]]) {\
UIViewController *vc = (UIViewController*)self;\
[vc presentViewController:alert animated:YES completion:nil];\
}else{\
[App.mainVC.contentNV presentViewController:alert animated:YES completion:nil];\
}\


//Get Color Function
#define rgba(R, G, B, A) [UIColor colorWithRed:(R)/255.0 green:(G)/255.0 blue:(B)/255.0 alpha:(A)]
#define RGBA(ColorLongValue, AlphaValue) [UIColor colorWithRed:((ColorLongValue>>16)&0xFF)/255.0 green:((ColorLongValue>>8)&0xFF)/255.0 blue:(ColorLongValue&0xFF)/255.0 alpha:AlphaValue]
#define RGB(ColorLongValue) RGBA(ColorLongValue, 1.0)

//Color
#define GREEN_COLOR RGB(0x69D554)
#define BLUE_COLOR RGB(0x0962A6)
#define RED_COLOR RGB(0xFC4837)
#define RED_BOOKING_COLOR RGB(0xD0021B)
#define GRAY_TAB_COLOR RGB(0xF9F9F9)
#define GRAY_TEXT_COLOR RGB(0x2A2D34)
#define GRAY_LIGHT_PLACEHOLDER_COLOR RGB(0xCCD6DD)
#define GRAY_DARK_COLOR RGB(0xCFCFCF)
#define GRAY_COLOR RGB(0xCCCCCC)
#define ORANGE_COLOR RGB(0xFF724B)
#define PURPLE_COLOR RGB(0x9885E2)
#define BLUE_BUTTON RGB(0x009DF7)
#define SHADOW_COLOR RGB(0X4C4C4C)
#define PASSED_COLOR RGB(0x00C79B)
#define WHITE_COLOR RGB(0xFFFFFF)

#define Caculator_INCOME RGB(0x4AD5B9)
#define Caculator_OUTCOME RGB(0xF6587B)

#pragma mark - CHECK Version IOS
#define SYSTEM_VERSION_EQUAL_TO(v)                  ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] == NSOrderedSame)
#define SYSTEM_VERSION_GREATER_THAN(v)              ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] == NSOrderedDescending)
#define SYSTEM_VERSION_GREATER_THAN_OR_EQUAL_TO(v)  ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] != NSOrderedAscending)
#define SYSTEM_VERSION_LESS_THAN(v)                 ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] == NSOrderedAscending)
#define SYSTEM_VERSION_LESS_THAN_OR_EQUAL_TO(v)     ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] != NSOrderedDescending)

#define STATUS_BAR_HEIGHT [[UIApplication sharedApplication]statusBarFrame].size.height

#define VALRANGE(VAL, MINVAL, MAXVAL) MAX(MINVAL, MIN(VAL, MAXVAL))
#define VALINRANGE(VALUE, MINVAL, MAXVAL) (((VALUE > MAXVAL) || (VALUE < MINVAL)) ? NO : YES)

#define IP6PLUS (SHEIGHT == 736.000000)
#define IP6 (SHEIGHT == 667.000000)
#define IP5 (SHEIGHT == 568.000000)
#define IP4S (SHEIGHT == 480.000000)
#define isLandscape() (SWIDTH > SHEIGHT)
#define isPortrait()  (SWIDTH < SHEIGHT)

#define VALCond(Condition, TrueValue, FalseValue)  ((Condition) ? (TrueValue) : (FalseValue))
#define VA(Value, AltValue)  ((Value) ? (Value) : (AltValue))

#define APPEND_LINE [[NSAttributedString alloc] initWithString:@"\n\n"]

// String preprocess
#define SF(A, ...) [NSString stringWithFormat:A, __VA_ARGS__]
#define SF_ATT(A, ...) [NSMutableAttributedString stringWithFormat:A, __VA_ARGS__]
#define SFCurrency(A) [Utilities getStringFromNumber:[NSNumber numberWithDouble:A]]
#define SFCurrencyHasCurrency(A) [Utilities getStringFromNumberHasCurrency:[NSNumber numberWithDouble:A]]


#define GET_COLLECTION_CELL(CellCls) CellCls *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@#CellCls forIndexPath:indexPath]
#define NSLOG_DELEGATE NSLog(@"%s:\nPlease implement delegate",__PRETTY_FUNCTION__);
#define NSLOG_DEBUG_FUNCTION(msg, ...) NSLog((@"%s [Line %d] " msg), __PRETTY_FUNCTION__, __LINE__, ##__VA_ARGS__);

#define ALERT_DEBUG if (data) {\
ALERT(@"ErrorMessage: %@, \nMessage: %@ \nAlert debug, will remove later", data.errorMessage, data.Message);\
}\

#define VID(A)  [Utilities validID:A]
#define GID(A)  [Utilities getValidID:A]
#define VID_NUM(A)  [Utilities validNumber:A]

#define ValidID(A)  [Utilities validID:A]
#define GetID(A)  [Utilities getValidID:A]
#define GetACronym(A)  [Utilities getValidACronym:A]


#define MId(A) [[A lowercaseString] stringByReplacingOccurrencesOfString:@" " withString:@"-"]
#define MId_Id(A) [[A lowercaseString] stringByReplacingOccurrencesOfString:@"\n" withString:@"-"]

// App Key
#define CF_UserData                 @"CF_UserData"
#define CF_ProjectData              @"CF_ProjectData"
#define CF_HasShownTermPrivacy      @"CF_HasShownTermPrivacy"
#define CF_CalendarFillter          @"CF_CalendarFillter"
#define CF_CalendarFillterProject   @"CF_CalendarFillterProject"

#define CF_UserIDNotification       @"CF_UserIDNotification"


// Date
#define DATE_IS_FUTURE  1
#define DATE_IS_TODAY   0
#define DATE_IS_PAST    -1


// DTO Preprocess
// Parse
#define IO(Obj)                 _##Obj = dic[@#Obj]
#define IOK(Obj,key)            _##Obj = dic[key]
#define IB(Obj)                 _##Obj = [dic[@#Obj] boolValue]
#define IC(Obj, ClassName)      _##Obj = [[ClassName alloc] initWithData:dic[@#Obj]]

#define IDate(Obj)              IDateF(Obj, [NSDateFormatter serverDateFormatter])
#define IDateF(Obj, DateFormat) _##Obj = [DateFormat dateFromString:dic[@#Obj]]
#define IN(Obj)                 _##Obj = [dic[@#Obj] isKindOfClass:[NSNumber class]]? [dic[@#Obj] integerValue]: 0
#define IL(Obj)                 _##Obj = [dic[@#Obj] isKindOfClass:[NSNumber class]]? [dic[@#Obj] longLongValue]: 0
#define ID(Obj)                 _##Obj = [dic[@#Obj] isKindOfClass:[NSNumber class]]? [dic[@#Obj] doubleValue]: 0.0
#define IF(Obj)                 _##Obj = [dic[@#Obj] isKindOfClass:[NSNumber class]]? [dic[@#Obj] floatValue]: 0.0

#define IA(Obj, ClassName) \
{ \
_##Obj = [[NSMutableArray alloc] init]; \
id items = dic[@#Obj]; \
if(items) { \
for(NSDictionary *item in items) { \
[_##Obj addObject:[[ClassName alloc] initWithData:item]]; \
} \
} \
}

#define IAK(Obj, ClassName, SubKey) \
{ \
_##Obj = [[NSMutableArray alloc] init]; \
id items = dic[@#Obj]; \
if(items) { \
for(NSDictionary *item in items) { \
[_##Obj addObject:[[ClassName alloc] initWithData:item[SubKey]]]; \
} \
} \
}


#define IAObj(Obj, Array, ClassName) \
{ \
_##Obj = [[NSMutableArray alloc] init]; \
if(Array) { \
for(NSDictionary *item in Array) { \
[_##Obj addObject:[[ClassName alloc] initWithData:item]]; \
} \
} \
}

#define IAS(Obj) \
{ \
_##Obj = [[NSMutableArray alloc] init]; \
id items = dic[@#Obj]; \
if(items) { \
for(NSString *item in items) { \
[_##Obj addObject:item]; \
} \
} \
}



// JSON MAP
#define JP(A) \
if(_##A) { \
NSString *pwd = PK(_##A); \
[dic setObject:pwd forKey:@#A]; \
}
#define JNN(Obj) JBKey(Obj, @#Obj)
#define JN(Obj)  JBNKey(Obj, @#Obj)
#define JB(Obj)  JBNKey(Obj, @#Obj)
#define JBN(Obj) JBKey(Obj, @#Obj)
#define JD(Obj)  JBKey(Obj, @#Obj)
#define JBKey(Obj, Key) \
if(_##Obj) { \
[dic setObject:@(_##Obj) forKey:Key]; \
}

#define JBNKey(Obj, Key) [dic setObject:@(_##Obj) forKey:Key]; \

#define JC(Obj) JCKey(Obj, @#Obj)
#define JCKey(Obj, Key) \
if(_##Obj) { \
[dic setObject:[_##Obj getJSONObject] forKey:Key]; \
}

#define JDate(Obj) JDateF(Obj, [NSDateFormatter serverDateFormatter])
#define JDateF(Obj, DateFormatter) if(_##Obj) [dic setObject:[DateFormatter stringFromDate:_##Obj] forKey:@#Obj]

#define JO(Obj) JOKey(Obj, @#Obj)
#define JOKey(Obj, Key) \
if(_##Obj) { \
[dic setObject:_##Obj forKey:Key]; \
}

#define JCI(Obj) JCIKey(Obj, @#Obj)
#define JCIKey(Obj, Key) \
if(_##Obj._id) { \
[dic setObject:_##Obj._id forKey:Key]; \
}

#define JAM(Obj, method) \
if(_##Obj) { \
NSMutableArray *arr = [[NSMutableArray alloc] init]; \
for(BaseDto *item in _##Obj) { \
[arr addObject:[item getJSONObjectWithMethod:method]]; \
} \
[dic setObject:arr forKey:@#Obj]; \
}

#define JA(Obj) \
if(_##Obj) { \
NSMutableArray *arr = [[NSMutableArray alloc] init]; \
for(BaseDto *item in _##Obj) { \
[arr addObject:[item getJSONObject]]; \
} \
[dic setObject:arr forKey:@#Obj]; \
}

#define JAK(A, Class, SubPath) \
NSMutableArray *arr##A = [[NSMutableArray alloc] init]; \
for(int i=0; i<_##A.count; i++) { \
Class *dto = _##A[i]; \
[arr##A addObject:dto.SubPath]; \
} \
[dic setObject:arr##A forKey:@#A];

#define JAC(A, Class, SubPath) \
NSMutableArray *arr##A = [[NSMutableArray alloc] init]; \
for(int i=0; i<_##A.list.count; i++) { \
Class *dto = _##A.list[i]; \
[arr##A addObject:dto.SubPath]; \
} \
[dic setObject:arr##A forKey:@#A];


// image
#define IM(A) [UIImage imageNamed:@#A]

// Clone
#define CO(Key) dto.Key = _##Key
#define CA(Key) \
if(_##Key.count > 0) { \
dto.Key = [[NSMutableArray alloc] init]; \
for (NSInteger i = 0; i< _##Key.count; i++) { \
[dto.Key addObject:_##Key[i]];\
}\
}

// Merge
#define MO(Key) _##Key = object.Key


#define PK(A) [Utilities getPassword:A]


// Term Privacy
#define Term_Mode_TermService   0
#define Term_Mode_Privacy       1
#define TermArray               @[@"Term", @"Privacy"]


// Storyboard
#define SB_ListFood       @"ListFood"
#define SB_Overview       @"Overview"
#define SB_Main           @"Main"
#define SB_Login          @"Login"
#define SB_Common         @"Common"
#define SB_Caculator      @"Caculator"

#define COMPANY_PLACEHODER_ICON @"CompanyLogo"
#define USER_PLACEHOLDER_ICON @"avatarCandidate"
#define ITEM_PLACEHOLDER_ICON @"avatarCandidate"
#define DOCUMENT_FILE_ICON @"Document.doc"

#define ModeDateTime UIDatePickerModeDateAndTime
#define ModeDate UIDatePickerModeDate
#define ModeTime UIDatePickerModeTime

#define DDF(A) ((A) ? [[NSDateFormatter displayDateFormatter] stringFromDate:A] : @"")



