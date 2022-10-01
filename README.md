# Object Detection App - TensorFlow Lite
### Overview
This is a camera app that continuously detects some objects (bounding boxes and classes) in the frames seen by your device's back camera, in real time, using my own trained model. The model trained with Transfer Learning technique from ssd_mobilenet_v2_quantized_coco pre-trained model.(https://github.com/tensorflow/models/blob/master/research/object_detection/g3doc/detection_model_zoo.md)
(https://github.com/tensorflow/examples/tree/master/lite/examples/object_detection/android)
The Phone's GPU is used, to make the detection faster.
The mobile app, presently, is able to recognize Desks, Chairs and TVs.
