//
//  MenuVC.m
//  FinacialManager_iOS
//
//  Created by ThanhSon on 3/17/18.
//  Copyright © 2018 ThanhSon. All rights reserved.
//

#import "MenuVC.h"
#import "MenuDto.h"
#import "UserDto.h"
#import "WelcomeVC.h"

typedef enum : NSUInteger {
    List = 0,
    Favourite,
    MoreApp,
    Update,
    Support,
    User,
} MenuList;

@interface MenuVC () <UITableViewDelegate, UITableViewDataSource, BaseCellDelegate> {
    MenuListDto *_listMenu;
}

@end

@implementation MenuVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initUI];
    [self initMenu];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)initUI {
    [UIView addShadowWithRadius:2.0 withShadowOpacity:0.8 withShadowOffset:CGSizeMake(0, 0) withShadowColor:GRAY_COLOR withCornerRadius:1 forView:_vLineMenu];
    [UIView addShadowWithRadius:2.0 withShadowOpacity:0.8 withShadowOffset:CGSizeMake(0, 0) withShadowColor:GRAY_COLOR withCornerRadius:1 forView:_vLineUser];
    _lblEmail.text = Config.userDto.email;
    _lblUserName.text= Config.userDto.userName;
}

#pragma mark initListMenu

- (void)initMenu {
    _listMenu = [[MenuListDto alloc] init];
    // ListMenu
    [_listMenu.list addObject:[[MenuDto alloc] initWithTitle:@"Nhu Cầu Thiết Yếu" andImage:@"ic_jar"]];
    [_listMenu.list addObject:[[MenuDto alloc] initWithTitle:@"Tiết Kiệm Dài Hạn" andImage:@"ic_jar"]];
    [_listMenu.list addObject:[[MenuDto alloc] initWithTitle:@"Đầu Tư Giáo Dục" andImage:@"ic_jar"]];
    [_listMenu.list addObject:[[MenuDto alloc] initWithTitle:@"Thụ Hưởng" andImage:@"ic_jar"]];
    [_listMenu.list addObject:[[MenuDto alloc] initWithTitle:@"Cho Đi" andImage:@"ic_jar"]];
    [_listMenu.list addObject:[[MenuDto alloc] initWithTitle:@"Tự Do Tài Chính" andImage:@"ic_jar"]];
}

#pragma mark Action
- (IBAction)btnLogout:(id)sender {
    App.configure.userDto = nil;
    WelcomeVC *vc = VCFromSB(WelcomeVC, SB_Login);
    [AppNav popToRootAndSwitchToViewController:vc withSlideOutAnimation:YES
                                 andCompletion:nil];
}

#pragma mark - UITableViewCell

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return  _listMenu.list.count;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 60;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    BaseCell * cell = [_tbvMenu dequeueReusableCellWithIdentifier:@"MenuCell"];
    
    MenuDto *menuDto = _listMenu.list[indexPath.row];
    cell.lblTitle.text = menuDto.title;
    cell.imgIcon.image = [UIImage imageNamed:menuDto.img];
    cell.lblTitle.textColor = [UIColor blackColor];
    
    
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    BaseCell *cell = (BaseCell*)[tableView cellForRowAtIndexPath:indexPath];
    UIView *cellBg = [[UIView alloc] initWithFrame:cell.frame];
    cellBg.backgroundColor = MAINCOLOR;
    cell.selectedBackgroundView = cellBg;
    cell.lblTitle.textColor = WHITE_COLOR;
    
}

- (void)tableView:(UITableView *)tableView didDeselectRowAtIndexPath:(NSIndexPath *)indexPath {
        BaseCell *cell = (BaseCell*)[tableView cellForRowAtIndexPath:indexPath];
        cell.lblTitle.textColor = [UIColor blackColor];
}

@end
