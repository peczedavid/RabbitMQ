import { useRef } from "react";
import axios from "axios";

import { Button, Form, Card, Container } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  const messageRef = useRef();
  const responseRef = useRef();

  function sendMessage(e) {
    e.preventDefault();
    const message = messageRef.current.value;
    if (message === "") return;
    axios
      .post("http://localhost:9000/message/publish", { message: message })
      .then((result) => {
        messageRef.current.value = "";
        if (result.status === 200) {
          const current = new Date();
          const options = { minimumIntegerDigits: 2, useGrouping: false };

          const year = current.getFullYear();
          const month = (current.getMonth() + 1).toLocaleString("en-US", options);;
          const day = current.getDate().toLocaleString("en-US", options);

          const hour = current.getHours().toLocaleString("en-US", options);
          const minute = current.getMinutes().toLocaleString("en-US", options);
          const second = current.getSeconds().toLocaleString("en-US", options);

          const date = `Message sent (${year}-${month}-${day} ${hour}:${minute}:${second})`;
          responseRef.current.innerHTML = date;
        } else responseRef.current.innerHTML = "Something went wrong";
      })
      .catch((error) => {
        console.log(error);
        responseRef.current.innerHTML = "Error";
      });
  }

  return (
    <Container className="h-50 d-flex justify-content-center align-items-center">
      <Card className="col-6 p-4">
        <Form onSubmit={sendMessage}>
          <Form.Group className="mb-3" controlId="formMessage">
            <Form.Label>Enter a message</Form.Label>
            <Form.Control ref={messageRef} type="text" placeholder="Message" />
          </Form.Group>

          <div className="d-flex justify-content-between">
            <div className="d-flex justify-content-start">
              <Button variant="primary" type="submit">
                Send
              </Button>
            </div>
            <div className="d-flex justify-content-end">
              <Form.Text
                ref={responseRef}
                className="text-muted pt-1"
              ></Form.Text>
            </div>
          </div>
        </Form>
      </Card>
    </Container>
  );
}

export default App;
