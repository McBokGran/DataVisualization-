import React, { useState, useEffect } from "react";

var stompClient = null;

const WebsocketService = (props) => {
  useEffect(() => {
    connectToFileScan();
  }, []);

  const connectToFileScan = () => {
    const Stomp = require("stompjs");
    var SockJS = require("sockjs-client");
    SockJS = new SockJS("http://localhost:8080/scan");
    stompClient = Stomp.over(SockJS);
    stompClient.connect({}, onConnected, onError);
  };

  const onConnected = () => {
    console.log("CONNECTED");
    stompClient.subscribe("/topic/notify", props.onMessageReceived);
  };

  const onError = (err) => {
    console.log("error", err);
  };

  return null;
};

export default WebsocketService;
