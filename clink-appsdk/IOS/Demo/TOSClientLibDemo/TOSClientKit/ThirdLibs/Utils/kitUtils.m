//
//  appUtils.m
//  TIMClientDemo
//
//  Created by YanBo on 2020/5/12.
//  Copyright © 2020 YanBo. All rights reserved.
//

#import "kitUtils.h"
#import <TOSClientLib/TIMClient.h>
#import "sys/utsname.h"
#import <CommonCrypto/CommonHMAC.h>
#import <MobileCoreServices/MobileCoreServices.h>
#import "NSDate+Common.h"

// 默认值为NO
static BOOL kKitLogEnable = NO;

static NSString * kitEVN_Config = @"";

#define kRandomLength 10

@implementation kitUtils

// 设置日志输出状态
+ (void)setLogEnable:(BOOL)enable {
    kKitLogEnable = enable;
}

// 设置环境 为了判断KT
+ (void)setEnvConf:(NSString *)env {
    kitEVN_Config = env;
}

// 获取环境 为了判断KT
+ (NSString*)getEnvConf {
    return kitEVN_Config;
}

// 获取日志输出状态
+ (BOOL)getLogEnable {
    return kKitLogEnable;
}

// 日志输出方法
+ (void)customLogWithFunction:(const char *)function lineNumber:(int)lineNumber formatString:(NSString *)formatString {
    if ([self getLogEnable]) {
        // 开启了Log
        NSLog(@"TIMKitDebug %s[%d]%@", function, lineNumber, formatString);
    }
}

+ (NSString *)getMsgUUID{
    return  [[NSUUID UUID] UUIDString];
}



+(NSString *)getNowTimestampWithSec{
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateStyle:NSDateFormatterMediumStyle];
    [formatter setTimeStyle:NSDateFormatterShortStyle];
    [formatter setDateFormat:@"YYYY-MM-dd HH:mm:ss"]; // ----------设置你想要的格式,hh与HH的区别:分别表示12小时制,24小时制
    //设置时区,这个对于时间的处理有时很重要
    NSTimeZone *timeZone = [NSTimeZone timeZoneWithName:@"Asia/Shanghai"];
    [formatter setTimeZone:timeZone];
    NSDate *datenow = [NSDate date]; //现在时间,你可以输出来看下是什么格式
    NSString *timeSp = [NSString stringWithFormat:@"%lld", (long long)[datenow timeIntervalSince1970] - [TIMClient sharedTIMClient].timeDifference];
    return timeSp;
}

+ (NSString *)md5StringFromString:(NSString *)string {
    NSParameterAssert(string != nil && [string length] > 0);

    const char *value = [string UTF8String];

    unsigned char outputBuffer[CC_MD5_DIGEST_LENGTH];
    CC_MD5(value, (CC_LONG)strlen(value), outputBuffer);

    NSMutableString *outputString = [[NSMutableString alloc] initWithCapacity:CC_MD5_DIGEST_LENGTH * 2];
    for(NSInteger count = 0; count < CC_MD5_DIGEST_LENGTH; count++){
        [outputString appendFormat:@"%02x", outputBuffer[count]];
    }

    return outputString;
}

+ (NSString *)RandomString{
    //随机字符串kRandomLength位
    static const NSString *kRandomAlphabet = @"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    NSMutableString *randomString = [NSMutableString stringWithCapacity: kRandomLength];
    for (int i = 0; i < kRandomLength; i++) {
        [randomString appendFormat: @"%C", [kRandomAlphabet characterAtIndex:arc4random_uniform((u_int32_t)[kRandomAlphabet length])]];
    }
    return randomString;
}

+ (NSString*)sha256HashFor:(NSString*)input{
    const char* str = [input UTF8String];
    unsigned char result[CC_SHA256_DIGEST_LENGTH];
    CC_SHA256(str, (CC_LONG)strlen(str), result);
    
    NSMutableString *ret = [NSMutableString stringWithCapacity:CC_SHA256_DIGEST_LENGTH*2];
    for(int i = 0; i<CC_SHA256_DIGEST_LENGTH; i++)
    {
        [ret appendFormat:@"%02x",result[i]];
    }
    ret = (NSMutableString *)[ret lowercaseString];
    return ret;
}

+(NSString *)getDeviceUDID{
    return [kitUtils getDeviceUDID];
}

+ (NSString *) removeTheLastOneStr:(NSString*)string{

    if([string length] > 0){
        return  [string substringToIndex:([string length]-1)];//去掉最后一个字符串如", ."
    }else{
        return string;
    }
}

+(NSString*)formattedDuration:(NSTimeInterval)interval{
    NSDateComponentsFormatter *formatter = [[NSDateComponentsFormatter alloc] init];
    formatter.allowedUnits = NSCalendarUnitHour | NSCalendarUnitMinute;
    formatter.zeroFormattingBehavior = NSDateComponentsFormatterZeroFormattingBehaviorPad;
    NSString *string = [formatter stringFromTimeInterval:interval];
    TIMKitLog(@"%@", string);
    // output: 0:20:34
    return string;
}

+(NSDate *)getDateTimeFromMilliSeconds:(long long)miliSeconds{
    NSTimeInterval tempMilli = miliSeconds;
    NSTimeInterval seconds = tempMilli/1000.0;//这里的.0一定要加上，不然除下来的数据会被截断导致时间不一致
    return [NSDate dateWithTimeIntervalSince1970:seconds];
}

+ (NSString *)getTimestampString:(long long)timestamp {
    NSDate *date = [kitUtils getDateTimeFromMilliSeconds:timestamp];
    NSString *dateString = [NSString stringWithFormat:@"%@",[kitUtils getDateString:date dateFormat:@"YYYY.MM.dd HH:mm"]];
    return dateString;
}

+ (NSString *)getDateString:(NSDate *)date dateFormat:(NSString *)format {
    NSDateFormatter *dateFormat = [[NSDateFormatter alloc] init];
    
    [dateFormat setDateFormat:format];
    //设置时区,这个对于时间的处理有时很重要
    NSTimeZone *timeZone = [NSTimeZone timeZoneWithName:@"Asia/Shanghai"];
    [dateFormat setTimeZone:timeZone];
    
    return [dateFormat stringFromDate:date];
}

+ (NSString*)DataTOjsonString:(id)object{
    NSString *jsonString = nil;
    NSError *error;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:object options:NSJSONWritingPrettyPrinted  error:&error];
    if(!jsonData) {
        TIMKitLog(@"Got an error: %@", error);
    } else {
        jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
//        NSLog(@"jsonStr==%@",jsonString);
    }
    return jsonString;
}

+ (NSDictionary *)dictionaryWithJsonString:(NSString *)jsonString {
    if (jsonString == nil) {
        return nil;
    }
    NSData *jsonData = [jsonString dataUsingEncoding:NSUTF8StringEncoding];
    NSError *err;
    NSDictionary *dic = [NSJSONSerialization JSONObjectWithData:jsonData
                                                        options:NSJSONReadingMutableContainers
                                                          error:&err];
    if(err) {
        TIMKitLog(@"json解析失败3：%@ json:%@",err,jsonString);
        return nil;
    }
    return dic;
}

// 判断为字串空
+ (BOOL)isBlankString:(NSString *)str {
    NSString *string = str;
    if (string == nil || string == NULL) {
        return YES;
    }
    if ([string isKindOfClass:[NSNull class]]) {
        return YES;
    }
    if (![string isKindOfClass:[NSString class]]) {
        return YES;
    }
    if ([string isEqualToString:@"NIL"] ||
        [string isEqualToString:@"Nil"] ||
        [string isEqualToString:@"nil"] ||
        [string isEqualToString:@"NULL"] ||
        [string isEqualToString:@"Null"] ||
        [string isEqualToString:@"null"] ||
        [string isEqualToString:@"(NULL)"] ||
        [string isEqualToString:@"(Null)"] ||
        [string isEqualToString:@"(null)"] ||
        [string isEqualToString:@"<NULL>"] ||
        [string isEqualToString:@"<Null>"] ||
        [string isEqualToString:@"<null>"] ) {
        return YES;
    }
    if ([[string stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceCharacterSet]] length]==0) {
        return YES;
    }
    
    return NO;
}

+(NSString *)dateStringFromNumberTimer:(NSString *)timerStr{
    //转化为Double
    double time=[timerStr doubleValue];
    //计算距离1970年的NSDate
    NSDate *date=[NSDate dateWithTimeIntervalSince1970:time];
    //转化为 时间字符串
    return [kitUtils formattedDateDescription:date];
}

+(NSString *)transferHHMMSSFromSS:(int)seconds{
    //format of hour
    NSString *str_hour = [NSString stringWithFormat:@"%02ld",seconds / 3600];
    //format of minute
    NSString *str_minute = [NSString stringWithFormat:@"%02ld",(seconds % 3600)/60];
    //format of second
    NSString *str_second = [NSString stringWithFormat:@"%02ld",seconds % 60];
    //format of time
    NSString *format_time = [NSString stringWithFormat:@"%@:%@:%@",str_hour,str_minute,str_second];
    if (seconds < 3600) {
        format_time = [NSString stringWithFormat:@"%@:%@",str_minute,str_second];
    }
    return format_time;
}

// 判断是否是当天
+ (BOOL)isSameDay:(long)iTime1 Time2:(long)iTime2
{
    //传入时间毫秒数
    NSDate *pDate1 = [NSDate dateWithTimeIntervalSince1970:iTime1/1000];
    NSDate *pDate2 = [NSDate dateWithTimeIntervalSince1970:iTime2/1000];
    NSCalendar* calendar = [NSCalendar currentCalendar];
    unsigned unitFlags = NSCalendarUnitYear | NSCalendarUnitMonth |  NSCalendarUnitDay;
    NSDateComponents* comp1 = [calendar components:unitFlags fromDate:pDate1];
    NSDateComponents* comp2 = [calendar components:unitFlags fromDate:pDate2];
    
    return [comp1 day]   == [comp2 day] &&
    [comp1 month] == [comp2 month] &&
    [comp1 year]  == [comp2 year];
}

+(NSString *)readNSUserDefaultsVoiceReadStatus:(NSString*)msgId from:(NSString*)from to:(NSString *)to
{
    NSUserDefaults *userDefaultes = [NSUserDefaults standardUserDefaults];

    //读取字典类型NSDictionary类型的数据
    NSArray * voiceReadArray = [userDefaultes arrayForKey:@"MutableVoiceReadArray"];

    for (NSDictionary *object in voiceReadArray) {
        if ([object objectForKey:@"msgId"] &&
            [object[@"msgId"] isEqualToString:msgId] &&
            [object objectForKey:@"from"] &&
            [object[@"from"] isEqualToString:from] &&
            [object objectForKey:@"to"] &&
            [object[@"to"] isEqualToString:to] &&
            [object objectForKey:@"read"]) {
            return object[@"read"];
        }
    }
    return @"";
}

+(void)saveNSUserDefaults:(NSDictionary *)Dictionary
{
    //将上述数据全部存储到NSUserDefaults中
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];

    NSArray * voiceReadArray = [userDefaults arrayForKey:@"MutableVoiceReadArray"];
    
    NSMutableArray * mutablReadArray = [NSMutableArray arrayWithArray:voiceReadArray];
    [mutablReadArray addObject:Dictionary];
    //存储时，除NSNumber类型使用对应的类型外，其他的都是使用setObject:forKey
    [userDefaults setObject:mutablReadArray forKey:@"MutableVoiceReadArray"];
    //这里建议同步存储到磁盘中，但是不是必须的
    [userDefaults synchronize];
}

// 获取url中的fileId
+ (NSString *)getFileIdFromUrl:(NSString *)fileUrl{
    NSString * strFileId = @"";
    // 参数解析 -- 非纯数字可以解析
    NSURL *url = [NSURL URLWithString:fileUrl];
    NSURLComponents *urlComponents = [NSURLComponents componentsWithURL:url resolvingAgainstBaseURL:NO];

    // url中参数的key value
    NSMutableDictionary *parameter = [NSMutableDictionary dictionary];
    for (NSURLQueryItem *item in urlComponents.queryItems) {
        if ([item.name isEqualToString:@"fileId"]) {
            strFileId = item.value;
        }
    }
    return strFileId;
}

+(NSString *)transferStopAction:(NSString *)stopAction isSender:(BOOL)isSender duration:(int)duration{
    NSString * retString = @"";
    NSString * showDurationString = @"";
    if (duration > 0) {
        showDurationString = [kitUtils transferHHMMSSFromSS:duration];
    }
    if (isSender) {
        if ([stopAction isEqualToString:@"cancel"]) {
            retString = @"已取消";
        } else if([stopAction isEqualToString:@"hangup"]){
            retString = [NSString stringWithFormat:@"通话时长 %@",showDurationString];
        } else if([stopAction isEqualToString:@"refuse"]){
            retString = [NSString stringWithFormat:@"对方已拒绝"];
        } else if([stopAction isEqualToString:@"no_response"]){
            retString = [NSString stringWithFormat:@"对方无应答"];
        } else if([stopAction isEqualToString:@"busy"]){
            retString = [NSString stringWithFormat:@"对方忙线中"];
        }
    } else {
        if ([stopAction isEqualToString:@"cancel"]) {
            retString = @"对方已取消";
        } else if([stopAction isEqualToString:@"hangup"]){
            retString = [NSString stringWithFormat:@"通话时长 %@",showDurationString];
        } else if([stopAction isEqualToString:@"refuse"]){
            retString = [NSString stringWithFormat:@"已拒绝"];
        } else if([stopAction isEqualToString:@"no_response"]){
            retString = [NSString stringWithFormat:@"未接通"];
        } else if([stopAction isEqualToString:@"busy"]){
            retString = [NSString stringWithFormat:@"忙线未接听"];
        }
    }
    return retString;
}

+ (NSString *)formattedDateDescription:(NSDate *)date {
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:@"yyyy-MM-dd"];
    NSString *theDay = [dateFormatter stringFromDate:date];//日期的年月日
    NSString *currentDay = [dateFormatter stringFromDate:[NSDate date]];//当前年月日
    if([theDay isEqualToString:currentDay]) {//当天
        return [date stringWithDateFormat:@"HH:mm"];
    } else if([date dayIndexSinceNow] == -1){//  昨天
        return [date stringWithDateFormat:@"昨天 HH:mm"];
    } else {
        return [date stringWithDateFormat:@"MM月dd日 HH:mm"];
    }
}


+ (UIImage *)imageNamed:(NSString *)name ofBundle:(NSString *)bundleName {
    
    static NSBundle *bundle = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        NSBundle *_bundle = [NSBundle bundleForClass:NSClassFromString(bundleName)];
        NSString *path = [_bundle pathForResource:bundleName ofType:@"bundle"];
        bundle = [NSBundle bundleWithPath:path];
    });
    NSBundle *_bundle = [NSBundle bundleForClass:NSClassFromString(bundleName)];
    NSString *bundlePath = [_bundle pathForResource:bundleName ofType:@"bundle"];
    bundle = [NSBundle bundleWithPath:bundlePath];
//    return bundle;
    
    
    

    UIImage *image = nil;

    NSString *image_name = [NSString stringWithFormat:@"%@.png", name];

//    NSString *resourcePath = [[NSBundle mainBundle] resourcePath];
//    NSString *bundlePath = [resourcePath stringByAppendingPathComponent:bundleName];

    NSString *image_path = [bundlePath stringByAppendingPathComponent:image_name];;

    image = [[UIImage alloc] initWithContentsOfFile:image_path];

    return image;

}

//path为要获取MIMEType的⽂件路径
+ (NSString *)mimeTypeForFileAtPath:(NSString *)path{
    if (![[[NSFileManager alloc] init] fileExistsAtPath:path]) {
        NSLog(@"mimeTypeForFileAtPath 默认 application/octet-stream");
        return @"application/octet-stream";
//        return nil;
    }
    CFStringRef UTI = UTTypeCreatePreferredIdentifierForTag(kUTTagClassFilenameExtension, (__bridge CFStringRef)[path pathExtension], NULL);
    CFStringRef MIMEType = UTTypeCopyPreferredTagWithClass (UTI, kUTTagClassMIMEType);
    CFRelease(UTI);
    if (!MIMEType) {
        return @"application/octet-stream";
    }
    NSLog(@"mimeTypeForFileAtPath = %@",(__bridge NSString *)(MIMEType));
    return (__bridge NSString *)(MIMEType);
}
//+ (UIImage*image)ybib_imageNamed:(NSString *)name bundle:(NSBundle *)bundle {
//    if (name.length == 0) return nil;
//    if ([name hasSuffix:@"/"]) return nil;
//
//    NSString *res = name.stringByDeletingPathExtension;
//    NSString *ext = name.pathExtension;
//    NSString *path = nil;
//    CGFloat scale = 1;
//
//    // If no extension, guess by system supported (same as UIImage).
//    NSArray *exts = ext.length > 0 ? @[ext] : @[@"", @"png", @"jpeg", @"jpg", @"gif", @"webp", @"apng"];
//    NSArray *scales = _NSBundlePreferredScales();
//    for (int s = 0; s < scales.count; s++) {
//        scale = ((NSNumber *)scales[s]).floatValue;
//        NSString *scaledName = _NSStringByAppendingNameScale(res, scale);
//        for (NSString *e in exts) {
//            path = [bundle pathForResource:scaledName ofType:e];
//            if (path) break;
//        }
//        if (path) break;
//    }
//    if (path.length == 0) {
//        // Assets.xcassets supported.
//        return [self imageNamed:name];
//    }
//
//    NSData *data = [NSData dataWithContentsOfFile:path];
//    if (data.length == 0) return nil;
//
//    return [[uiimage alloc] initWithData:data scale:scale];
//}

//NSBundle *YBIBVideoBundle(void) {
//    static NSBundle *bundle = nil;
//    static dispatch_once_t onceToken;
//    dispatch_once(&onceToken, ^{
//        NSBundle *_bundle = [NSBundle bundleForClass:NSClassFromString(@"YBImageBrowser")];
//        NSString *path = [_bundle pathForResource:@"YBImageBrowserVideo" ofType:@"bundle"];
//        bundle = [NSBundle bundleWithPath:path];
//    });
//    return bundle;
//}

+ (NSDictionary *)getPlistFile:(NSString *)plistName{
    //首先读取studentInfo.plist中的数据
    NSBundle *bundle = [NSBundle bundleWithPath:[[NSBundle mainBundle] pathForResource:@"TOSClient" ofType:@"bundle"]];
    NSString *plistPath = [bundle pathForResource:plistName ofType:@"plist"];
    return [[NSDictionary alloc] initWithContentsOfFile:plistPath];
    
}

    //获取当前屏幕显示的viewcontroller
+ (UIViewController *)XG_GetController {
   // UIApplication.shared.delegate?.window!!.rootViewController
    UIViewController *rootViewController = [[[[UIApplication sharedApplication] delegate] window] rootViewController];
    UIViewController *currentViewController = [self XG_GetCurrentViewControllerFrom:rootViewController];
    return currentViewController;
}

+ (UIViewController *)XG_GetCurrentViewControllerFrom:(UIViewController *)rootViewController {
    UIViewController *currentViewController;

    if ([rootViewController presentedViewController]) {
        //视图是被presented出来的
        rootViewController = [rootViewController presentedViewController];
    }

    if ([rootViewController isKindOfClass:[UITabBarController class]]) {
        //根视图为UITabBarController
        currentViewController = [self XG_GetCurrentViewControllerFrom:[(UITabBarController *)rootViewController selectedViewController]];
    } else if ([rootViewController isKindOfClass:[UINavigationController class]]) {
        //根视图为UINavigationController
        currentViewController = [self XG_GetCurrentViewControllerFrom:[(UINavigationController *)rootViewController visibleViewController]];
    } else {
        //根视图为非导航类
        currentViewController = rootViewController;
    }
    return currentViewController;
}

+ (NSString *)convertToJsonDataWithDic:(NSDictionary *)dic {
    if (dic &&
        [dic isKindOfClass:[NSDictionary class]] &&
        [dic allKeys].count > 0) {
        
        NSError *error;
        NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dic options:NSJSONWritingPrettyPrinted error:&error];
        NSString *jsonString;
        if (!jsonData) {
            NSLog(@"%@",error);
        } else {
            jsonString = [[NSString alloc]initWithData:jsonData encoding:NSUTF8StringEncoding];
        }
        NSMutableString *mutStr = [NSMutableString stringWithString:jsonString];
//        NSRange range = {0,jsonString.length};
        //去掉字符串中的空格
//        [mutStr replaceOccurrencesOfString:@" " withString:@"" options:NSLiteralSearch range:range];
        NSRange range2 = {0,mutStr.length};
        //去掉字符串中的换行符
        [mutStr replaceOccurrencesOfString:@"\n" withString:@"" options:NSLiteralSearch range:range2];
        return mutStr;
    } else {
        
        return @"数据格式不正确";
    }
}

+ (BOOL)isTextTypeLabel:(NSString *)type {
    if ([type isEqualToString:@"h1"] ||
        [type isEqualToString:@"h2"] ||
        [type isEqualToString:@"h3"] ||
        [type isEqualToString:@"h4"] ||
        [type isEqualToString:@"h5"] ||
        [type isEqualToString:@"h6"] ||
        [type isEqualToString:@"tr"] ||
        [type isEqualToString:@"ul"] ||
        [type isEqualToString:@"ol"] ||
        [type isEqualToString:@"img"] ||
        [type isEqualToString:@"video"] ||
        [type isEqualToString:@"br"] ||
        [type isEqualToString:@"div"] ||
        [type isEqualToString:@"colgroup"]) {
        return NO;
    } else {
        return YES;
    }
}

@end
