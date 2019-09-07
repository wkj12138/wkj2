import React, { Component } from "react";
// import Trans from "./trans";
// import Login from "./Login";
// import Ne from "./Ne";
// import Choose from "./Choose";
// import Apply from "./apply/Apply";
import WrappedNormalLoginForm from "./login/Login";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      test: "中英文双向翻译"
    };
  }
  render() {
    return (
      <div>
        {/* <Apply></Apply> */}
        <WrappedNormalLoginForm></WrappedNormalLoginForm>
        {/* <Wfl></Wfl> */}
        {/* <Ne></Ne> */}
        {/* <Choose></Choose> */}
      </div>
    );
  }
}
export default App;
