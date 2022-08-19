import * as faceapi from "face-api.js";
import React from "react";

function Face(props) {
  const [modelsLoaded, setModelsLoaded] = React.useState(false);
  const [captureVideo, setCaptureVideo] = React.useState(false);

  const videoRef = React.useRef();
  const videoHeight = 480;
  const videoWidth = 640;
  const canvasRef = React.useRef();
  let flag;
  let age, gender; //app jsx로 보내야됨

  // const websocket = new WebSocket("wss://i7d207.p.ssafy.io/ws/socket");
  // let message;

  // websocket.onmessage = onMessage;
  // websocket.onopen = onOpen;
  // websocket.onclose = onClose;

  // //채팅창에서 나갔을 때
  // function onClose(evt) {
  //   if (!isOpen(websocket)) return;
  // }

  // //채팅창에 들어왔을 때
  // function onOpen(evt) {
  //   if (!isOpen(websocket)) return;
  // }

  // function onMessage(msg) {
  //   if (!isOpen(websocket)) return;

  //   var data = msg.data;
  //   message = null;

  //   //현재 세션에 로그인 한 사람
  //   message = data;

  //   return message;
  // }

  // function isOpen(ws) {
  //   return ws.readyState === ws.OPEN;
  // }

  React.useEffect(() => {
    const loadModels = async () => {
      const MODEL_URL = process.env.PUBLIC_URL + "/models";

      Promise.all([
        faceapi.nets.tinyFaceDetector.loadFromUri(MODEL_URL),
        faceapi.nets.faceLandmark68Net.loadFromUri(MODEL_URL),
        faceapi.nets.faceRecognitionNet.loadFromUri(MODEL_URL),
        faceapi.nets.faceExpressionNet.loadFromUri(MODEL_URL),
        faceapi.nets.ssdMobilenetv1.loadFromUri(MODEL_URL),
        faceapi.nets.ageGenderNet.loadFromUri(MODEL_URL),
      ]).then(setModelsLoaded(true));
    };
    loadModels();
  }, []);

  const startVideo = () => {
    setCaptureVideo(true);
    navigator.mediaDevices
      .getUserMedia({ video: { width: 300 } })
      .then((stream) => {
        let video = videoRef.current;
        video.srcObject = stream;
        video.play();
      })
      .catch((err) => {
        console.error("error:", err);
      });
  };

  function sendData(sendage, sendgender) {
    props.setAge(parseInt(sendage / 1));
    props.setGender(sendgender);
  }
  const handleVideoOnPlay = () => {
    let intervalId = setInterval(async () => {
      if (flag) {
        clearInterval(intervalId); //인식 하기 전까지 계속 돌다가 인식하면 모델 종료
        closeWebcam();
      }
      if (canvasRef && canvasRef.current) {
        canvasRef.current.innerHTML = faceapi.createCanvasFromMedia(
          videoRef.current
        );
        const displaySize = {
          width: videoWidth,
          height: videoHeight,
        };

        faceapi.matchDimensions(canvasRef.current, displaySize);

        const detections = await faceapi
          .detectSingleFace(
            videoRef.current,
            new faceapi.TinyFaceDetectorOptions()
          )
          .withFaceLandmarks()
          .withFaceExpressions()
          .withAgeAndGender()
          .withFaceDescriptor();
        flag = detections;
        const resizedDetections = faceapi.resizeResults(
          detections,
          displaySize
        );
        age = resizedDetections.age;
        // console.log(age + "나이");
        gender = resizedDetections.gender;
        // console.log(gender + "성별");
        props.setAge(resizedDetections.age);
        props.setGender(resizedDetections.age);

        sendData(age, gender);

        canvasRef &&
          canvasRef.current &&
          canvasRef.current
            .getContext("2d")
            .clearRect(0, 0, videoWidth, videoHeight);
        canvasRef &&
          canvasRef.current &&
          faceapi.draw.drawDetections(canvasRef.current, resizedDetections);
        canvasRef &&
          canvasRef.current &&
          faceapi.draw.drawFaceLandmarks(canvasRef.current, resizedDetections);
        canvasRef &&
          canvasRef.current &&
          faceapi.draw.drawFaceExpressions(
            canvasRef.current,
            resizedDetections
          );
      }
    }, 100);
  };

  const closeWebcam = () => {
    videoRef.current.pause();
    videoRef.current.srcObject.getTracks()[0].stop();
    setCaptureVideo(false);

    // console.log(message);
    // if (window.location.pathname == "/") {
    //   if (Number(message) >= 150) {
    //     window.location.assign(
    //       window.location.href + "default",
    //       props.moviesData
    //     );
    //   } else if (Number(message) < 150 && 100 < Number(message)) {
    //     window.location.assign(window.location.href + "low", props.moviesData);
    //   }
    // }
    // console.log(window.location);

    // console.log("https://i7d207.p.ssafy.io/api/movies/" + movie_code[0]);
    // axios
    //   .get("https://i7d207.p.ssafy.io/api/movies/" + movie_code[0])
    //   .catch(function (err) {
    //     console.log(err, "default 데이터x");
    //   })
    //   .then(function (response) {
    //     // 성공 핸들링
    //     console.log(response.data);
    //     state = response.data;
    //   });
    // console.log(state);
  };

  return (
    <div>
      <button
        onClick={startVideo}
        style={{
          cursor: "pointer",
          backgroundColor: "green",
          color: "white",
          padding: "15px",
          fontSize: "25px",
          border: "none",
          borderRadius: "10px",
        }}
      >
        Open Webcam
      </button>

      {captureVideo ? (
        modelsLoaded ? (
          <div>
            <div
              style={{
                display: "flex",
                justifyContent: "center",
                padding: "10px",
              }}
            >
              <video
                ref={videoRef}
                height={videoHeight}
                width={videoWidth}
                onPlay={handleVideoOnPlay}
                style={{ borderRadius: "10px" }}
              />
              <canvas
                ref={canvasRef}
                style={{ position: "absolute", display: "none" }}
              />
            </div>
          </div>
        ) : (
          <div>loading...</div>
        )
      ) : (
        <></>
      )}
    </div>
  );
}
export default Face;
