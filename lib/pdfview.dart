import 'dart:async';

import 'package:flutter/services.dart';


  const MethodChannel _channel =
      const MethodChannel('pdfview');

  Future<dynamic> pdfview(String text) {
    assert(text != null && text.isNotEmpty);

    return _channel.invokeMethod('pdfview', text);
  }

