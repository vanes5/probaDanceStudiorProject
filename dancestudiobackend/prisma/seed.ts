import { PrismaClient } from '@prisma/client'
import { faker } from '@faker-js/faker';

const prisma = new PrismaClient()
async function main() {
  for (let i = 0; i < 15; i++) {
    await prisma.applications.create({
      data: {
        course_id: faker.number.int({ min: 1, max: 10}),
        price: faker.number.int({min: 1000, max:15000})
      }
    })
  }
}
main()
  .then(async () => {
    await prisma.$disconnect()
  })
  .catch(async (e) => {
    console.error(e)
    await prisma.$disconnect()
    process.exit(1)
  })
