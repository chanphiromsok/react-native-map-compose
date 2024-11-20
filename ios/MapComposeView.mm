#ifdef RCT_NEW_ARCH_ENABLED
#import "MapComposeView.h"

#import "ComponentDescriptors.h"
#import "EventEmitters.h"
#import "Props.h"
#import "RCTComponentViewHelpers.h"

#import "RCTFabricComponentsPlugins.h"
#import "Utils.h"

using namespace facebook::react;

@interface MapComposeView () <RCTMapComposeViewViewProtocol>

@end

@implementation MapComposeView {
    UIView * _view;
}

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    return concreteComponentDescriptorProvider<MapComposeViewComponentDescriptor>();
}

- (instancetype)initWithFrame:(CGRect)frame
{
  if (self = [super initWithFrame:frame]) {
    static const auto defaultProps = std::make_shared<const MapComposeViewProps>();
    _props = defaultProps;

    _view = [[UIView alloc] init];

    self.contentView = _view;
  }

  return self;
}

- (void)updateProps:(Props::Shared const &)props oldProps:(Props::Shared const &)oldProps
{
    const auto &oldViewProps = *std::static_pointer_cast<MapComposeViewProps const>(_props);
    const auto &newViewProps = *std::static_pointer_cast<MapComposeViewProps const>(props);

    if (oldViewProps.color != newViewProps.color) {
        NSString * colorToConvert = [[NSString alloc] initWithUTF8String: newViewProps.color.c_str()];
        [_view setBackgroundColor: [Utils hexStringToColor:colorToConvert]];
    }

    [super updateProps:props oldProps:oldProps];
}

Class<RCTComponentViewProtocol> MapComposeViewCls(void)
{
    return MapComposeView.class;
}

@end
#endif
