//
//  BaiduMapViewController.m
//  TOSClientKitDemo
//
//  Created by 言 on 2022/8/25.
//

#import "BaiduMapViewController.h"

#import <BaiduMapAPI_Base/BMKBaseComponent.h>//引入base相关所有的头文件
#import <BaiduMapAPI_Map/BMKMapComponent.h>//引入地图功能所有的头文件

@interface BaiduMapViewController () <BMKMapViewDelegate>

@property (nonatomic, strong) BMKMapView *mapView;

@end

@implementation BaiduMapViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.mapView = [[BMKMapView alloc]initWithFrame:self.view.bounds];
    self.mapView.delegate = self;
    self.mapView.showsUserLocation = YES;
    [self.view addSubview:self.mapView];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.mapView viewWillAppear];
}

- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
    [self.mapView viewWillDisappear];
}

@end
