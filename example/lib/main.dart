import 'package:flutter/material.dart';
import 'package:pdfview/pdfview.dart';

void main() {
  runApp(new DemoApp());
}

class DemoApp extends StatefulWidget {
  @override
  DemoAppState createState() => new DemoAppState();
}

class DemoAppState extends State<DemoApp> {
  String text = '';

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'Pdf View Plugin Demo',
      home: new Scaffold(
          appBar: new AppBar(
            title: const Text('Pdf View Plugin Demo'),
          ),
          body: new Padding(
            padding: const EdgeInsets.all(24.0),
            child: new Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                new TextField(
                  decoration: const InputDecoration(
                    labelText: 'Text:',
                    hintText: 'Enter some text',
                  ),
                  maxLines: 4,
                  onChanged: (String value) => setState(() {
                    text = value;
                  }),
                ),
                new RaisedButton(
                  child: const Text('PDF View'),
                  onPressed: text.isNotEmpty
                      ? () {
                    pdfview(text);
                  }
                      : null,
                ),
                new RaisedButton(
                  child: const Text('HTML PDF View'),
                  onPressed: () {
                    pdfview("<h1>Title</h1><table border=1><tr><td>Table</td><td>Test</td></tr></table>");
                  },
                ),
              ],
            ),
          )),
    );
  }
}
