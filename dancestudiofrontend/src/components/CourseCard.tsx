import {Course} from "../types/Course.ts";
import {Button, Card} from "react-bootstrap";
import {useState} from "react";
import {applyToCourse} from "../lib/api.ts";

interface CourseCardProps{
    course: Course
}

export default function CourseCard({course}: CourseCardProps){
    const [message, setMessage] = useState<string>("");


    const handleApplication = async () => {
        try{
            await applyToCourse(course.id);
            setMessage("Sikeres jelentkezés")
        }
        catch (e: any) {
            setMessage(e.message);
        }
    }

    return <>
        <Card className={"border-2 col-lg-4 col-md-6 col-sm-12 col-12"} style={{ width: '18rem'}}>
            <Card.Body>
                <Card.Title>{course.name}</Card.Title>
                <Card.Text>Hossz: {course.length} perc</Card.Text>
                <Card.Text>Tánctanár: {course.instructor}</Card.Text>
                <Card.Img variant="top" src={`${course.type}.svg`} />
                <Button variant="primary" onClick={handleApplication}>Jelentkezés</Button>
                {
                    message && (
                        <p>{message}</p>
                    )
                }
            </Card.Body>
        </Card>
    </>
}