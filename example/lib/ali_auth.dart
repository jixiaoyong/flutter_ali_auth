import 'package:ali_auth/ali_auth.dart';

/// 示例类，展示如何使用 ali_auth 插件
class ExampleAliAuth {
  /// 初始化 SDK
  static Future<void> initSdk() async {
    try {
      await AliAuth.initSdk(null);
      print('SDK 初始化成功');
    } catch (e) {
      print('SDK 初始化失败: $e');
    }
  }

  /// 开始一键登录
  static Future<void> login() async {
    try {
      await AliAuth.login();
      print('登录成功');
    } catch (e) {
      print('登录失败: $e');
    }
  }

  /// 设置协议勾选框状态
  static Future<void> setProtocolChecked(bool isChecked) async {
    try {
      await AliAuth.setProtocolChecked(isChecked);
      print('设置协议勾选框状态成功: $isChecked');
    } catch (e) {
      print('设置协议勾选框状态失败: $e');
    }
  }

  /// 关闭授权页面
  static Future<void> quitPage() async {
    try {
      await AliAuth.quitPage();
      print('关闭授权页面成功');
    } catch (e) {
      print('关闭授权页面失败: $e');
    }
  }

  /// 添加登录监听
  static void addLoginListener() {
    AliAuth.loginListen(
      onEvent: (event) {
        print('登录事件: $event');
      },
      onError: (error) {
        print('登录错误: $error');
      },
    );
  }
} 