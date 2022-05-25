import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_mapbox/flutter_mapbox.dart';

void main() {
  const MethodChannel channel = MethodChannel('flutter_mapbox');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    MapBoxNavigation directions = MapBoxNavigation();
    expect(await directions.platformVersion, '42');
  });
}