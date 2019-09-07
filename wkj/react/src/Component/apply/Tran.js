import React, { Component } from "react";
import axios from "axios";
import { Card, Table, Button, Input } from "antd";
class Tran extends Component {
  constructor(props) {
    super(props);
    this.state = {
      trans: ""
    };
    this.translations = this.translations.bind(this);
  }
  translations() {
    //将插入的值插入到"Input_word"中
    var word = document.getElementById("Input_word").value;
    axios.get("http://localhost:8099/trans/" + word).then(response => {
      console.log(response);
      this.setState({
        trans: response.data.Translation_word.replace(/\|/g, "\n")
      });
    });
  }
  render() {
    return (
      <div>
        <Card title={"中英翻译"}>
          <Input
            placeholder="Please input the word"
            id="Input_word"
            style={{
              margin: "10px",
              width: "300px"
            }}
          />

          <Button type="primary" onClick={this.translations}>
            中英翻译
          </Button>

          <Input.TextArea
            value={this.state.trans}
            style={{
              height: "250px"
            }}
          />
        </Card>
      </div>
    );
  }
}
export default Tran;
