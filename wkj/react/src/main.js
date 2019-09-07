import React from "react";
import ReactDOM from "react-dom";
import "antd/dist/antd.css";
import { LocaleProvider } from "antd";

import App from "./component/app";
ReactDOM.render(
  <LocaleProvider locale="zhCN">
    <App />
  </LocaleProvider>,
  document.getElementById("app")
);
