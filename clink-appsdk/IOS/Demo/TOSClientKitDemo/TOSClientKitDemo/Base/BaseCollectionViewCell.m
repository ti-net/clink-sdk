//
//  BaseCollectionViewCell.m
//  SmartHome
//
//  Created by 赵言 on 2019/7/9.
//  Copyright © 2019 赵言. All rights reserved.
//

#import "BaseCollectionViewCell.h"

@implementation BaseCollectionViewCell

+ (instancetype)cellWithCollectionView:(UICollectionView *)collectionView reuseIdentifie:(NSString *)reuseIdentifie indexPath:(NSIndexPath *)indexPath {
    [collectionView registerClass:NSClassFromString(reuseIdentifie) forCellWithReuseIdentifier:reuseIdentifie];
    return [collectionView dequeueReusableCellWithReuseIdentifier:reuseIdentifie forIndexPath:indexPath];
}

+ (instancetype)cellXibWithCollectionView:(UICollectionView *)collectionView reuseIdentifie:(NSString *)reuseIdentifie indexPath:(NSIndexPath *)indexPath {
    [collectionView registerNib:[UINib nibWithNibName:reuseIdentifie bundle:nil] forCellWithReuseIdentifier:reuseIdentifie];
    return [collectionView dequeueReusableCellWithReuseIdentifier:reuseIdentifie forIndexPath:indexPath];
}

- (instancetype)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        [self setupSubviews];
    }
    return self;
}

- (instancetype)initWithCoder:(NSCoder *)coder {
    self = [super initWithCoder:coder];
    if (self) {
        [self setupSubviews];
    }
    return self;
}

- (void)setWithModel:(id)model {
    
    
}

- (void)setupSubviews {
    
}

- (void)layoutSubviews {
    [super layoutSubviews];
    [self defineLayout];
}

- (void)defineLayout {
    
}

@end
