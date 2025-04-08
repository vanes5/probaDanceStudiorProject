import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import {useEffect, useState} from "react";
import {Course} from "./types/Course.ts";
import {getCourses} from "./lib/api.ts";
import CourseCard from "./components/CourseCard.tsx";
import {Container, Nav, Navbar} from "react-bootstrap";

function App() {
  const [courses, setCourses] = useState<Course[]>([])

  useEffect(() => {
    document.title = "Petrik Táncstúdió"

    const fetchData = async () => {
      await getCourses().then((res) => {
        setCourses(res);
      })
    }
    fetchData();

  }, []);



  return (
    <>
      <header>
        <Navbar expand="lg" className="bg-body-tertiary">
          <Container>
            <h1>Petrik táncstúdió</h1>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="me-auto">
                <Nav.Link href="#footer">Elérhetőség</Nav.Link>
                <Nav.Link href="https://petrik.hu">Petrik honlap</Nav.Link>
              </Nav>
            </Navbar.Collapse>
          </Container>
        </Navbar>
      </header>
      <body>
        <div className={"row justify-content-center "}>
          {
            courses.map((item: Course) =>
                <CourseCard key={item.id} course={item}/>
            )
          }
        </div>
      </body>
      <footer id={"footer"}>
        <p>Cím: 1440 Budapest, Ady Endre utca 2-4</p>
        <p>Telefon: +36 40 123 4567</p>
        <p>Készítette: Gecse Vanessza Katalin</p>
      </footer>

    </>
  )
}

export default App
