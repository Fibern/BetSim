using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UnitTests
{
    public class OffertTests
    {
        [Theory]
        [InlineData(2,50)]
        [InlineData(2.50,40)]
        [InlineData(1.50,66.67)]
        [InlineData(1.66,60.24)]
        public void courseToPercent_returnPercent(double course, double score)
        {
            //arrange
            var offert = new Offert();
            
            //act
            double percent = offert.CourseToPercent(course);
            percent = Math.Round(percent, 2);

            //assert
            Assert.Equal(percent, score);
        }

        [Theory]
        [InlineData(1.66, 2.50)]
        public void ValidateCourse_ReturnTrue(double course1, double course2)
        {
            //Act
            Offert offert = new Offert()
            {
                Odds = new List<Odd>
                {
                    new Odd
                    {
                        OddValue = course1,
                    },
                    new Odd
                    {
                        OddValue = course2,
                    },
                },
            };

            //Arrange
            bool result = offert.ValidateOdds();

            //Assert
            Assert.Equal(result, true);
        }

        [Theory]
        [InlineData(1.69, 2.50)]
        public void ValidateCourse_ReturnFalse(double course1, double course2)
        {
            //Act
            Offert offert = new Offert()
            {
                Odds = new List<Odd>
                {
                    new Odd
                    {
                        OddValue = course1,
                    },
                    new Odd
                    {
                        OddValue = course2,
                    },
                },
            };

            //Arrange
            bool result = offert.ValidateOdds();

            //Assert
            Assert.Equal(result, true);
        }
    }
}
